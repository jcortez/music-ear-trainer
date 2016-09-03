import { Directive, ElementRef, Renderer, HostListener, Output, EventEmitter } from '@angular/core'
import { ButtonEnabledDirective } from './button-enabled.directive';

@Directive({
  selector: '[ciButtonEnabled]'
})
export class ChordInversionButtonEnabledDirective extends ButtonEnabledDirective {
  @Output() chordInversionButtonEnabled = new EventEmitter();

  constructor(private chordInversionEl: ElementRef, renderer: Renderer) {
    super(chordInversionEl, renderer);
  }

  @HostListener('click') onButtonClicked() {
    super.onButtonClicked();
    this.chordInversionButtonEnabled.emit({ element: this.chordInversionEl, buttonEnabled: super.buttonIsEnabled() });
  }

}
