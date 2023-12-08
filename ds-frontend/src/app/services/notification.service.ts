import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { INotification, NotificationType } from '../models/notification.models';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private notification$: Subject<INotification> = new BehaviorSubject({} as INotification);

  constructor() {

  }

  error(message: string, duration?: number) {
    this.notify(message, NotificationType.Error, duration)
  }
  warning(message: string, duration?: number) { 
    this.notify(message, NotificationType.Warning, duration)
  }
  success(message: string, duration?: number) {
    this.notify(message, NotificationType.Success, duration)
  }

  private notify(message : string, type: NotificationType, duration?: number) {
    duration = !duration ? 3000 : duration;
    this.notification$.next({message, type, duration} as INotification)
  }

  get notification() {
    return this.notification$.asObservable();
  }

}
