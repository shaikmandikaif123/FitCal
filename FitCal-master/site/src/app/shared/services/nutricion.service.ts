import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { Day } from '@shared/interfaces/dayInterface';
import { FoodInstance } from '@shared/interfaces/foodInstanceInterface';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NutritionService {
  private readonly API_URL_FOODINSTANCE = environment.foodInstanceUrl;

  constructor(private http: HttpClient) { }

  searchByDayId(dayId: number): Observable<FoodInstance[]> {
    const url = `${this.API_URL_FOODINSTANCE}/search/${dayId}`;
    return this.http.get<FoodInstance[]>(url);
  }
}
