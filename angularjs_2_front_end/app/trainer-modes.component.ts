import {Component, Input, Output, EventEmitter} from '@angular/core'

@Component({
    selector: 'trainer-mode',
    templateUrl: 'app/trainer-mode.component.html',
    styleUrls: ['app/trainer-mode.component.css']
})
export class TrainerModeComponent {
  @Input() trainerMode;
  @Output('selected') select = new EventEmitter();

  onSelect() {
    this.select.emit(this.trainerMode);
  }
}
