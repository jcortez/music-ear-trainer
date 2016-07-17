import { Component } from '@angular/core';
import { QuestionService } from './question.service';
import { Chord } from './chord';
import { Answer } from './answer';
import { AnswerResponse } from './answer-response';
import { RouteParams } from '@angular/router-deprecated';
declare var MIDI: any;

@Component({
  selector: 'chord-tester',
  templateUrl: 'app/chord-tester.component.html',
  styleUrls: ['app/chord-tester.component.css'],
  providers: [QuestionService]
})
// The view that plays the music file for the chord and tests that the user
// selects the correct chord.
// TODO: Refactor into super class and have ChordTester subclasses for different
// game modes. Only override getNextQuestion() in subclasses because this is what
// will differ.
export class ChordTester {
  // All possible chord roots that can be selected for the chord.
  public TESTER_CHORD_ROOTS = [ "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb",
  "G", "G#/Ab", "A", "A#/Bb", "B/Cb" ];
  public testerChordQualities;
  public testerChordInversions;
  // The user's answer made up of the selected chord characteristics.
  private currentAnswer: Answer;

  constructor(private questionService: QuestionService, private routeParams:
    RouteParams) { }

  ngOnInit() {
    let routerChordQualities = this.routeParams.get('trainerChordQualities');
    let routerChordInversions = this.routeParams.get('trainerChordInversions');
    // Checking to make sure router parameters exist.
    if (routerChordQualities === "" || routerChordQualities === null ||
      Object.keys(routerChordQualities).length === 0 ||
      routerChordInversions === "" || routerChordInversions === null ||
      Object.keys(routerChordInversions).length === 0) {
      window.alert("Error: Please start the Music Ear Trainer again.");
      return;
    }

    this.testerChordQualities = routerChordQualities;
    this.testerChordInversions = routerChordInversions;
    this.getNextQuestion().then(output => this.playQuestionMIDIFile());
  }

  onChordRootSelected(root) {
    this.currentAnswer.answer.chordRoot = root;
  }

  onChordQualitySelected(quality) {
    this.currentAnswer.answer.chordQuality = quality;
  }

  onChordInversionSelected(inversion) {
    this.currentAnswer.answer.chordInversion = inversion;
  }

  onPlayAgainButtonClicked() {
    this.playQuestionMIDIFile();
  }

  onSubmitButtonClicked() {
    if (!this.allChordCharacteristicsSelected()) {
      // If the user has not selected all chord characteristics, then a
      // message is displayed.
      window.alert("Please select one of each chord characteristic");
      return;
    }
    this.checkUserAnswer();
    this.getNextQuestion().then(output => this.playQuestionMIDIFile());
  }

  // Gets the next question from the server.
  private getNextQuestion() : Promise<string> {
    // Resetting the current answer.
    this.currentAnswer = new Answer();
    return this.questionService.getQuestionCustomMode(this.testerChordQualities,
    this.testerChordInversions).then(question => this.currentAnswer.questionMidiFileName
    = question.questionMidiFileName).catch(error => window.alert(error));
  }

  // Plays the MIDI file in the web browser for the question.
  private playQuestionMIDIFile() {
    let midiFile = this.currentAnswer.questionMidiFileName;
    if (midiFile === "" || midiFile === undefined) {
      window.alert("ERROR: Could not load MIDI file.");
      return;
    }

    MIDI.loadPlugin({ soundfontUrl: "midijs/soundfont/",
      onsuccess: function() {
          MIDI.Player.loadFile("midi/" + midiFile, MIDI.Player.start);
      }
    });
  }

  // Processes the question answer response from the server and reports if the
  // user's answer was correct or not.
  private processAnswerResponse(answerResponse : AnswerResponse) {
    if (answerResponse.userAnswerCorrect) {
      window.alert("Your answer was correct!");
    }
    else {
      window.alert("Your answer was incorrect! The correct chord was " +
        answerResponse.correctAnswer.chordRoot + " " +
        answerResponse.correctAnswer.chordQuality + " " +
        answerResponse.correctAnswer.chordInversion);
    }
  }

  // Checks the user's answer by sending it to the server.
  private checkUserAnswer() {
    this.questionService.checkAnswerCustomMode(this.currentAnswer)
    .then(answerResponse => this.processAnswerResponse(answerResponse))
    .catch(error => window.alert(error));
  }

  // Checks that all chord characteristics have been selected in the GUI.
  private allChordCharacteristicsSelected() : boolean {
    if (this.currentAnswer.answer.chordRoot === "" ||
        this.currentAnswer.answer.chordQuality === "" ||
        this.currentAnswer.answer.chordInversion === "") {
      return false;
    }
    else {
      return true;
    }
  }

}
