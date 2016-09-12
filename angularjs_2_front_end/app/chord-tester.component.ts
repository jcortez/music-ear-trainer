import { Component, ElementRef, Renderer } from '@angular/core';
import { QuestionService } from './question.service';
import { Chord } from './chord';
import { Answer } from './answer';
import { AnswerResponse } from './answer-response';
import { ActivatedRoute, Params } from '@angular/router';
import { BUTTON_ENABLED_CSS_CLASS } from './button-enabled.directive';
import { InfoService } from './info.service';
declare var MIDI: any;

@Component({
  selector: 'chord-tester',
  templateUrl: 'app/chord-tester.component.html',
  styleUrls: ['app/chord-tester.component.css'],
  providers: [ QuestionService, InfoService ]
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
  public answerSubmitted: boolean;
  public correctAnswer: Chord;
  public userAnswerIsCorrect: boolean;
  public testerChordQualities;
  public testerChordInversions;
  // The user's answer made up of the selected chord characteristics.
  private currentAnswer: Answer;
  public currentAnswerString: string;
  public currentNumberOfCorrectAnswers: number;
  public currentNumberOfQuestionsAsked: number;
  public currentScorePercentage: number;
  // The ButtonEnabledDirective will set these when the appropriate event is raised.
  private currentSelectedChordRoot: ElementRef;
  private currentSelectedChordQuality: ElementRef;
  private currentSelectedChordInversion: ElementRef;
  // The pressed piano keys to show in the GUI. Each key is stored in scientific
  // notation.
  public pianoKeys: string[];
  // Used to convert MIDI notes to scientific notation.
  private static NOTE_NAMES = [ 'C', 'Db', 'D', 'Eb', 'E', 'F', 'Gb', 'G', 'Ab', 'A', 'Bb', 'B'];

  constructor(private questionService: QuestionService, private activatedRoute:
    ActivatedRoute, private renderer: Renderer, private infoService: InfoService) { }

  ngOnInit() {
    this.answerSubmitted = false;
    this.currentAnswerString = "";
    this.userAnswerIsCorrect = false;
    this.currentNumberOfCorrectAnswers = 0;
    this.currentNumberOfQuestionsAsked = 0;
    this.currentScorePercentage = 0;
    this.pianoKeys = [ ];

    let routerChordQualities = "";
    let routerChordInversions = "";
    this.activatedRoute.params.forEach((param: Params) => {
      routerChordQualities = param['trainerChordQualities'].split(',');
      routerChordInversions = param['trainerChordInversions'].split(',');
    });
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

  ngAfterViewInit() {
    window.alert("Please click on the Chord Root, Chord Quality, and Chord Inversion buttons to make your selection and then click the Submit button.");
  }

  onChordRootSelected(root) {
    // There is no easy way to accomplish this with an attribute directive, so
    // this logic (and similarly for the chord quality and inversion) has to be
    // done here.
    if (this.currentSelectedChordRoot !== undefined &&
        this.currentSelectedChordRoot.nativeElement.textContent !== root) {
      this.renderer.setElementClass(this.currentSelectedChordRoot.nativeElement,
        BUTTON_ENABLED_CSS_CLASS, false);
    }

    // Checking if chord root button was disabled to unset the selected root.
    if (this.currentAnswer.answer.chordRoot === root) {
      this.currentAnswer.answer.chordRoot = "";
    }
    else {
      this.currentAnswer.answer.chordRoot = root;
    }
    this.showSelectedAnswerInGUI();
  }

  onChordQualitySelected(quality) {
    if (this.currentSelectedChordQuality !== undefined &&
        this.currentSelectedChordQuality.nativeElement.textContent !== quality) {
      this.renderer.setElementClass(this.currentSelectedChordQuality.nativeElement,
        BUTTON_ENABLED_CSS_CLASS, false);
    }

    // Checking if chord quality button was disabled to unset the selected quality.
    if (this.currentAnswer.answer.chordQuality === quality) {
      this.currentAnswer.answer.chordQuality = "";
    }
    else {
      this.currentAnswer.answer.chordQuality = quality;
    }
    this.showSelectedAnswerInGUI();
  }

  onChordInversionSelected(inversion) {
    if (this.currentSelectedChordInversion !== undefined &&
        this.currentSelectedChordInversion.nativeElement.textContent !== inversion) {
      this.renderer.setElementClass(this.currentSelectedChordInversion.nativeElement,
        BUTTON_ENABLED_CSS_CLASS, false);
    }

    // Checking if chord inversion button was disabled to unset the selected inversion.
    if (this.currentAnswer.answer.chordInversion === inversion) {
      this.currentAnswer.answer.chordInversion = "";
    }
    else {
      this.currentAnswer.answer.chordInversion = inversion;
    }
    this.showSelectedAnswerInGUI();
  }

  // Showing selected chord characteristics as answer in GUI if the user has
  // selected from all characteristics.
  private showSelectedAnswerInGUI() {
    if (this.allChordCharacteristicsSelected()) {
      this.currentAnswerString = this.currentAnswer.answer.chordRoot + " "
        + this.currentAnswer.answer.chordQuality + " "
        + this.currentAnswer.answer.chordInversion;
      this.infoService.getMidiNotesForChord(this.currentAnswer.answer)
        .then((allMidiNotes) => this.pianoKeys = allMidiNotes.midiNotes.map(this.convertMidiToScientificNotation))
        .catch(error => window.alert(error._body));
    }
    else {
      this.currentAnswerString = "";
      this.pianoKeys = [ ];
    }
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
  }

  onNextQuestionButtonClicked() {
    this.getNextQuestion().then(output => this.playQuestionMIDIFile());
  }

  // Gets the next question from the server.
  private getNextQuestion() : Promise<string> {
    // Resetting the current answer.
    this.currentAnswer = new Answer();
    this.currentAnswerString = "";
    this.pianoKeys = [ ];
    this.answerSubmitted = false;
    this.userAnswerIsCorrect = false;
    this.correctAnswer = undefined;

    if (this.currentSelectedChordRoot !== undefined) {
      this.renderer.setElementClass(this.currentSelectedChordRoot.nativeElement,
        BUTTON_ENABLED_CSS_CLASS, false);
    }
    if (this.currentSelectedChordQuality !== undefined) {
      this.renderer.setElementClass(this.currentSelectedChordQuality.nativeElement,
        BUTTON_ENABLED_CSS_CLASS, false);
    }
    if (this.currentSelectedChordInversion !== undefined) {
      this.renderer.setElementClass(this.currentSelectedChordInversion.nativeElement,
        BUTTON_ENABLED_CSS_CLASS, false);
    }
    return this.questionService.getQuestionCustomMode(this.testerChordQualities,
    this.testerChordInversions).then(question => this.currentAnswer.questionMidiFileName
    = question.questionMidiFileName).catch(error => window.alert(error._body));
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
  // user's answer was correct or not. The score will also be updated in the
  // GUI.
  private processAnswerResponse(answerResponse : AnswerResponse) {
    if (answerResponse.userAnswerCorrect) {
      this.currentNumberOfCorrectAnswers++;
      this.userAnswerIsCorrect = true;
    }
    else {
      this.userAnswerIsCorrect = false;
    }
    this.currentNumberOfQuestionsAsked++;
    this.currentScorePercentage = Math.round(this.currentNumberOfCorrectAnswers/
      this.currentNumberOfQuestionsAsked*100);
    this.correctAnswer = answerResponse.correctAnswer;
    this.answerSubmitted = true;
    this.pianoKeys = answerResponse.midiNotes.map((midiNote) => this.convertMidiToScientificNotation(midiNote));
  }

  // Checks the user's answer by sending it to the server.
  private checkUserAnswer() {
    this.questionService.checkAnswerCustomMode(this.currentAnswer)
    .then(answerResponse => this.processAnswerResponse(answerResponse))
    .catch(error => window.alert(error._body));
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

  // Called when the chordRootButtonEnabled event is raised.
  public setCurrentSelectedChordRoot($event) {
    if ($event.buttonEnabled) {
      this.currentSelectedChordRoot = $event.element;
    }
    else {
      this.currentSelectedChordRoot = undefined;
    }
  }

  // Called when the chordQualityButtonEnabled event is raised.
  public setCurrentSelectedChordQuality($event) {
    if ($event.buttonEnabled) {
      this.currentSelectedChordQuality = $event.element;
    }
    else {
      this.currentSelectedChordQuality = undefined;
    }
  }

  // Called when the chordInversionButtonEnabled event is raised.
  public setCurrentSelectedChordInversion($event) {
    if ($event.buttonEnabled) {
      this.currentSelectedChordInversion = $event.element;
    }
    else {
      this.currentSelectedChordInversion = undefined;
    }
  }

  // Converts MIDI notes to scientific notation. Adapted from
  // https://github.com/danigb/tonal/tree/master/modules/midi (MIT License).
  private convertMidiToScientificNotation(midiNote: number) {
    let noteName = ChordTester.NOTE_NAMES[midiNote % 12];
    let octave = Math.floor(midiNote / 12) - 1;
    return noteName + octave;
  }

}
