import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainComponent } from './routes/main/main.component';
import { AuthComponent } from './routes/auth/auth.component';
import { PointFormComponent } from './components/point-form/point-form.component';
import {ReactiveFormsModule} from "@angular/forms";
import { RegisterComponent } from './routes/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    AuthComponent,
    PointFormComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
