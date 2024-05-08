import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { SelectedFood } from '@shared/interfaces/AlimentoSeleccionado';

import { Day } from '@shared/interfaces/dayInterface';
import { FoodInstance } from '@shared/interfaces/foodInstanceInterface';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiaryService {
  private readonly API_URL_DAY = environment.dayUrl;
  private readonly API_URL_FOODINSTANCE = environment.foodInstanceUrl;
  date!: Date;
  mealType!: string;

  selectedFood: SelectedFood | null = null;

  constructor(private http: HttpClient) { }

  addSelectedFood(food: SelectedFood) {
    this.selectedFood = food;
  }

  getSelectedFood(): SelectedFood | null {
    return this.selectedFood;
  }

  private enableEdit: boolean = true;

  setEnableEdit(value: boolean) {
    this.enableEdit = value;
  }

  getEnableEdit() {
    return this.enableEdit;
  }

  setMealType(value: string) {
    this.mealType = value;
  }

  getMealType() {
    return this.mealType;
  }

  /**
   * Database queries.
   */

  /** Day table */
  getDayByDateAndUser(date: Date, user_id: number) {
    return this.http.get<Day>(`${this.API_URL_DAY}/${user_id}?date=${date}`);
  }

  searchByDateAndUser(date: string, user_id: number): Observable<Day[]> {
    const url = `${this.API_URL_DAY}/search?date=${date}&userId=${user_id}`;
    return this.http.get<Day[]>(url);
  }

  createDay(day: Day) {
    return this.http.post<Day>(this.API_URL_DAY, day);
  }

  /** FoodInstance table */
  getFoodInstancesByDay(dayId: number): Observable<FoodInstance[]> {
    const url = `${this.API_URL_FOODINSTANCE}/search/${dayId}`;
    return this.http.get<FoodInstance[]>(url);
  }

  createFoodInstance(foodInstance: FoodInstance) {
    return this.http.post<FoodInstance>(this.API_URL_FOODINSTANCE, foodInstance);
  }

  deleteFoodInstance(id:number){
    return this.http.delete<FoodInstance>(`${this.API_URL_FOODINSTANCE}/${id}`); }
}
