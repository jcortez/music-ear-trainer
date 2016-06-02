import { Component } from '@angular/core';
import { CHORD_QUALITIES, CHORD_INVERSIONS } from './mock-custom-mode-characteristics';
import { QuestionService } from './question.service';
import { Chord } from './chord';
import { Answer } from './answer';
import { AnswerResponse } from './answer-response';

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
  // TODO: Keep track of characteristics selected for custom mode and only show
  // these in GUI.
  public testerChordQualities = CHORD_QUALITIES;
  public testerChordInversions = CHORD_INVERSIONS;
  // The user's answer made up of the selected chord characteristics.
  private currentAnswer: Answer;

  constructor(private questionService: QuestionService) { }

  ngOnInit() {
    this.getNextQuestion();
    this.playQuestionMIDIFile();
  }

  onChordRootSelected(root) {
    this.currentAnswer.chosenChord.chordRoot = root;
    console.log(root + " root selected");
  }

  onChordQualitySelected(quality) {
    this.currentAnswer.chosenChord.chordQuality = quality;
    console.log(quality + " quality selected");
  }

  onChordInversionSelected(inversion) {
    this.currentAnswer.chosenChord.chordInversion = inversion;
    console.log(inversion + " inversion selected");
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
    this.getNextQuestion();
    this.playQuestionMIDIFile();
  }

  // Gets the next question from the server.
  private getNextQuestion() {
    // Resetting the current answer.
    this.currentAnswer = new Answer();
    this.questionService.getQuestionCustomMode(this.testerChordQualities,
    this.testerChordInversions).then(midiFile => this.currentAnswer.midiFileName
    = midiFile).catch(error => window.alert(error));
  }

  // Plays the MIDI file in the web browser for the question.
  private playQuestionMIDIFile() {
    console.log("Playing MIDI file: " + this.currentAnswer.midiFileName);
  }

  // Processes the question answer response from the server and reports if the
  // user's answer was correct or not.
  private processAnswerResponse(answerResponse : AnswerResponse) {
    if (answerResponse.userAnswerIsCorrect) {
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
    if (this.currentAnswer.chosenChord.chordRoot === "" ||
        this.currentAnswer.chosenChord.chordQuality === "" ||
        this.currentAnswer.chosenChord.chordInversion === "") {
      return false;
    }
    else {
      return true;
    }
  }

}
