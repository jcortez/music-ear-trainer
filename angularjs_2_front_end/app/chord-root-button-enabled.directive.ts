import { Directive, ElementRef, Renderer, HostListener, Output, EventEmitter } from '@angular/core'
import { ButtonEnabledDirective } from './button-enabled.directive';

@Directive({
  selector: '[crButtonEnabled]'
})
export class ChordRootButtonEnabledDirective extends ButtonEnabledDirective {
  @Output() chordRootButtonEnabled = new EventEmitter();

  constructor(private chordRootEl: ElementRef, renderer: Renderer) {
    super(chordRootEl, renderer);
  }

  @HostListener('click') onButtonClicked() {
    super.onButtonClicked();
    this.chordRootButtonEnabled.emit({ element: this.chordRootEl, buttonEnabled: super.buttonIsEnabled() });
  }

}
