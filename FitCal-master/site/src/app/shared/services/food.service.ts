import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { Food } from '@shared/interfaces/foodInterface';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FoodService {
  private readonly API_URL = environment.foodUrl;

  private selectedFoodSubject = new BehaviorSubject<any>(null);
  selectedFood$ = this.selectedFoodSubject.asObservable();

  selectFood(food: any) {
    this.selectedFoodSubject.next(food);
  }

  constructor(private http: HttpClient) {}

  getFood() {
    return this.http.get<Food[]>(this.API_URL);
  }

  getFoodById(id: number) {
    return this.http.get<Food>(`${this.API_URL}/${id}`);
  }

  createFood(food: Food) {
    return this.http.post<Food>(this.API_URL, food);
  }

  deleteFood(foodId: number) {
    return this.http.delete(`${this.API_URL}/${foodId}`);
  }

  updateFood(food: Food) {
    return this.http.put<Food>(`${this.API_URL}/${food.id}`, food);
  }
}
