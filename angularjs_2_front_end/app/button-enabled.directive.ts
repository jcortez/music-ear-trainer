import { Directive, ElementRef, Renderer, HostListener } from '@angular/core'

export const BUTTON_ENABLED_CSS_CLASS = "button-enabled";

@Directive({
  selector: '[buttonEnabled]'
})
// Attribute directive to style buttons when they are clicked on and enabled
// or disabled by the user.
export class ButtonEnabledDirective {
  constructor(private el: ElementRef, private renderer: Renderer) { }

  // Determines whether the button is enabled or not by seeing if the button
  // has the button-enabled CSS class attached.
  protected buttonIsEnabled(): boolean {
    return this.el.nativeElement.classList.contains(BUTTON_ENABLED_CSS_CLASS);
  }

  // Toggles enabled/disabled button style.
  @HostListener('click') onButtonClicked() {
    if (!this.buttonIsEnabled()) {
      this.renderer.setElementClass(this.el.nativeElement, BUTTON_ENABLED_CSS_CLASS, true);
    }
    else {
      this.renderer.setElementClass(this.el.nativeElement, BUTTON_ENABLED_CSS_CLASS, false);
    }
  }

}
