import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LeaseCreateComponent } from './components/lease-create/lease-create.component';
import { LeaseReturnComponent } from './components/lease-return/lease-return.component';
import { LeaseListComponent } from './components/lease-list/lease-list.component';
import { ConfirmModalComponent } from './components/confirm-modal/confirm-modal.component';
import { ToastComponent } from './components/toast/toast.component';
import { StoreModule } from '@ngrx/store';
import { leaseReducer } from './store/lease.reducer';
import { EffectsModule } from '@ngrx/effects';
import { LeaseEffects } from './store/lease.effects';

@NgModule({
  declarations: [
    AppComponent,
    LeaseCreateComponent,
    LeaseReturnComponent,
    LeaseListComponent,
    ConfirmModalComponent,
    ToastComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    StoreModule.forRoot({ lease: leaseReducer }),
    EffectsModule.forRoot([LeaseEffects])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
