import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ITag } from '../models/tag.models';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TagService {

  constructor(
    private readonly httpClient : HttpClient
  ) { }

  private readonly baseURL : string = "http://localhost:8080/tags"

  public getTags() : Observable<ITag[]> {
    return this.httpClient.get<ITag[]>(this.baseURL);
  }
}
