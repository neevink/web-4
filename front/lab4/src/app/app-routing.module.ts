import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthComponent } from './routes/auth/auth.component';
import { MainComponent } from './routes/main/main.component';

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    /* canActivate: [AuthGraudService] */
  },
  {
    path: 'signup',
    component: AuthComponent
  },
  {
    path: '**',
    redirectTo: ''
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
