import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FoodComponent } from 'src/app/pages/food/food.component';
import { DialogCreateFoodComponent } from '../dialog-create-food/dialog-create-food.component';
import { FoodService } from '@shared/services/food.service';
import { DiaryService } from '@shared/services/diary.service';
import { FoodInstance } from '@shared/interfaces/foodInstanceInterface';
import { DateService } from '@shared/services/date.service';
import { Day } from '@shared/interfaces/dayInterface';
import { AuthService } from '@shared/services/auth.service';

@Component({
  selector: 'app-meal',
  templateUrl: './meal.component.html',
  styleUrls: ['./meal.component.scss']
})
export class MealComponent implements OnInit {
  @Input() meal: string = '';
  @Input() mealCalories: number = 0;
  @Input() foods: FoodInstance[] = []; // Update the type of foods to FoodInstance[]
  enableEdit = false;
  buttonId = '';
  modalFoodOpen = false;
  user: any;

  constructor(
    private matDialog: MatDialog,
    private foodService: FoodService,
    private diaryService: DiaryService,
    private dateService: DateService,
    private fitcalAuthService: AuthService,
  ) {
    this.user = fitcalAuthService.getUser();
  }

  ngOnInit() {
    this.foodService.selectedFood$.subscribe(food => {
      if (food && this.meal === this.buttonId) {
        this.foods.push(food);
        this.buttonId = "";
      }
    });

    // Call a function to filter and set up foods according to meal type    
    this.setFoodsByMealType();
  }
  
  removeItem(index: number, id: number): void {
    const itemId = this.foods[index].id!;
    this.diaryService.deleteFoodInstance(itemId).subscribe(success => {
      window.location.reload();
    })
  }

  addFoodModal() {
    this.buttonId = document.getElementById(this.meal)!.id;

    this.diaryService.setEnableEdit(false);
    this.diaryService.setMealType(this.buttonId);

    const dialogRef = this.matDialog.open(FoodComponent, {
      width: '1300px',
      height: '600px',
      data: {
        enableEdit: this.enableEdit,
        mealType: this.buttonId,
        margin: true
      }
    });
  }

  // Function to filter and set up foods according to the meal type
  setFoodsByMealType() {
    this.foods = this.foods.filter(food => food.mealType === this.meal.toUpperCase());
  }
}
