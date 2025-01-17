import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { User } from '@shared/interfaces/userInterface';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly API_URL = environment.usersUrl;

  constructor(private http: HttpClient) { }

  getUsers() {
    return this.http.get<User[]>(this.API_URL);
  }

  getUserById(id: number) {
    return this.http.get<User>(`${this.API_URL}/${id}`);
  }

  createUser(user: User) {
    return this.http.post<User>(this.API_URL, user);
  }

  updateUser(user: User) {
    return this.http.put<User>(`${this.API_URL}/${user.id}`, user);
  }

  checkUserExists(email: string) {
    return this.http.get<User>(`${this.API_URL}/${email}`);
  }
}
