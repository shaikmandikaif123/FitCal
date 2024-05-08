import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './pages/profile/profile.component';
import { NutritionComponent } from './pages/nutrition/nutrition.component';
import { DiaryComponent } from './pages/diary/diary.component';
import { LoginGuardService } from '@shared/services/loginGuard.service';
import { FoodComponent } from 'src/app/pages/food/food.component';
import { FechaComponentComponent } from '@shared/components/fecha-component/fecha-component.component';

const routes: Routes = [
  { path: '', redirectTo: 'profile', pathMatch: 'full' },
  { path: 'profile', component: ProfileComponent },
  { path: 'diary', component: DiaryComponent, canActivate: [LoginGuardService] },
  { path: 'nutrition', component: NutritionComponent, canActivate: [LoginGuardService] },
  { path: 'foods', component: FoodComponent, canActivate: [LoginGuardService] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
