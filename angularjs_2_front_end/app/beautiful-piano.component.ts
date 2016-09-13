import { Component, ElementRef, Renderer, Input, SimpleChange } from '@angular/core';
declare var piano: any;
// The MIDI note for the last piano key that will be shown.
export var LAST_MIDI_PIANO_KEY = 83;

// AngularJS 2 component for the beautiful-piano library.
@Component({
  selector: 'beautiful-piano',
  template: '',
})
export class BeautifulPiano {
  // An array of the piano keys that are pressed. Each key is stored in scientific
  // notation.
  @Input() pianoKeysPressed: string[];

  constructor(private componentElementRef: ElementRef, private renderer: Renderer) { }

  ngOnInit() {
    // Initialize beautiful-piano DOM element.
    piano(this.componentElementRef.nativeElement, {
      range: {
        startKey: 'C', startOctave: 4, endKey: 'B', endOctave: 5
      }
    });
  }

  ngOnChanges(changes: {[propKey: string]: SimpleChange}) {
    for (let propName in changes) {
      let changedProp = changes[propName];
      let from: string[] = changedProp.previousValue;
      let to: string[] = changedProp.currentValue;

      for (let key of from) {
        this.setPianoKeyPressed(key, false);
      }
      for (let key of to) {
        this.setPianoKeyPressed(key, true);
      }
    }
  }

  // Enables/disables the CSS style to press a piano key.
  private setPianoKeyPressed(key: string, pressed: boolean): void {
    let keyElement = this.getPianoKeyElement(key);

    if (keyElement === undefined) {
      window.alert("Error: Please start the Music Ear Trainer again.");
      return;
    }

    this.renderer.setElementClass(keyElement, "active", pressed);
  }

  // Finds the element for the piano key in the DOM. Undefined is returned if
  // it cannot be found. There is currently no way to accomplish this using
  // @ViewChild/@ViewChildren because the beautiful-piano key elements are not
  // direct descendants, so direct DOM manipulation must be done.
  private getPianoKeyElement(keyName: string): any {
    let keyClass = "." + keyName;
    return this.componentElementRef.nativeElement.querySelector(keyClass);
  }
}
