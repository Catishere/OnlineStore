import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { AuthGuard } from '../authentication';
import { AdminComponent } from "./admin.component";

const adminRoutes: Routes = [
    { path: 'admin', component: AdminComponent, data: {role: 'ROLE_ADMIN'}, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(adminRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class AdminRoutingModule {}
