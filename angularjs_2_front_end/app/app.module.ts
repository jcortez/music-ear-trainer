import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HttpModule } from '@angular/http'
import { TrainerModesComponent } from './trainer-modes.component';
import { CustomModeCharacteristics } from './custom-mode-characteristics.component';
import { ChordTester } from './chord-tester.component';
import { routing } from './app.routing';
import { ButtonEnabledDirective } from './button-enabled.directive';
import { ChordRootButtonEnabledDirective } from './chord-root-button-enabled.directive';
import { ChordQualityButtonEnabledDirective } from './chord-quality-button-enabled.directive';
import { ChordInversionButtonEnabledDirective } from './chord-inversion-button-enabled.directive';
import { BeautifulPiano } from './beautiful-piano.component';
import { ChordTesterAnswerStyleDirective } from './chord-tester-answer-style.directive';

@NgModule({
    declarations: [
      AppComponent,
      TrainerModesComponent,
      CustomModeCharacteristics,
      ChordTester,
      ButtonEnabledDirective,
      ChordRootButtonEnabledDirective,
      ChordQualityButtonEnabledDirective,
      ChordInversionButtonEnabledDirective,
      BeautifulPiano,
      ChordTesterAnswerStyleDirective
    ],
    imports:      [ BrowserModule, HttpModule, routing ],
    bootstrap:    [ AppComponent ],
})
export class AppModule { }
