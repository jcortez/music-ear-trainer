import { Component, ElementRef, Renderer } from '@angular/core';
declare var piano: any;

// AngularJS 2 component for the beautiful-piano library.
@Component({
  selector: 'beautiful-piano',
  template: '',
})
export class BeautifulPiano {
  //TODO: use @Input for changing array of piano keys to press? (how to detect input has changed?)

  constructor(private el: ElementRef, private renderer: Renderer) { }

  ngOnInit() {
    // Initialize beautiful-piano DOM element.
    piano(this.el.nativeElement, {
      range: {
        startKey: 'C', startOctave: 4, endKey: 'B', endOctave: 5
      }
    });
  }
}
