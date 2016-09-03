import { Directive, ElementRef, Renderer, HostListener, Output, EventEmitter } from '@angular/core'
import { ButtonEnabledDirective } from './button-enabled.directive';

@Directive({
  selector: '[cqButtonEnabled]'
})
export class ChordQualityButtonEnabledDirective extends ButtonEnabledDirective {
  @Output() chordQualityButtonEnabled = new EventEmitter();

  constructor(private chordQualityEl: ElementRef, renderer: Renderer) {
    super(chordQualityEl, renderer);
  }

  @HostListener('click') onButtonClicked() {
    super.onButtonClicked();
    this.chordQualityButtonEnabled.emit({ element: this.chordQualityEl, buttonEnabled: super.buttonIsEnabled() });
  }

}
