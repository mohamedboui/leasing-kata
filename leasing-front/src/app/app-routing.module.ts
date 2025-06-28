import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LeaseCreateComponent } from './components/lease-create/lease-create.component';
import { LeaseReturnComponent } from './components/lease-return/lease-return.component';
import { LeaseListComponent } from './components/lease-list/lease-list.component';

const routes: Routes = [
  { path: '', component: LeaseListComponent },
  { path: 'leases/new', component: LeaseCreateComponent },
  { path: 'leases/return', component: LeaseReturnComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
