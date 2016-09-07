import { Directive, Input, ElementRef, Renderer } from '@angular/core';

@Directive({
  selector: '[chordTesterAnswer]'
})
// Directive that sets the CSS style of the Chord Tester answer text.
export class ChordTesterAnswerStyleDirective {
  @Input() answerCorrect: boolean;

  constructor(private el: ElementRef, private renderer: Renderer) { }

  ngOnInit() {
    if (this.answerCorrect) {
      this.renderer.setElementClass(this.el.nativeElement, "answer-correct", true);
    }
    else {
      this.renderer.setElementClass(this.el.nativeElement, "answer-incorrect", true);
    }
  }

}
