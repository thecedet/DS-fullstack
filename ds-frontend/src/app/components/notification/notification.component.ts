import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { NotificationService } from '../../services/notification.service';
import { INotification, NotificationType } from '../../models/notification.models';
import { takeWhile } from 'rxjs';

@Component({
  selector: 'app-notification',
  standalone: true,
  imports: [],
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css'
})
export class NotificationComponent implements OnInit{
  
  @ViewChild('notificationContainer') container ?: ElementRef<HTMLDivElement>;
  
  private _subscribed: boolean = true;
  private classMap: Map<NotificationType, string> = new Map<NotificationType, string>();

  constructor(
    private service  : NotificationService,
    private renderer : Renderer2
  ) {}

  ngOnInit(): void {
    this.classMap.set(NotificationType.Success, 'success');
    this.classMap.set(NotificationType.Error, 'error');
    this.classMap.set(NotificationType.Warning, 'warning');

    this.service.notification
      .pipe(takeWhile(() => this._subscribed))
      .subscribe(notification => {
        if(notification) this.render(notification)
      }
    );
  }

  ngOnDestroy() {
    this._subscribed = false;
  }

  private render(notification: INotification) {
    let notificationBox = this.renderer.createElement("span")
    let content = this.renderer.createElement("span")

    const boxColorClass = this.classMap.get(notification.type);
    let classesToAdd = ['alert', boxColorClass]

    classesToAdd.forEach(className => {
      if(className) return this.renderer.addClass(notificationBox, className)
    })

    this.renderer.setStyle(notificationBox, 'transition', `opacity ${notification.duration}ms`);
    this.renderer.setStyle(notificationBox, 'opacity', '.7');

    const text = this.renderer.createText(notification.message)
    this.renderer.appendChild(content, text)

    if(this.container) {
      this.renderer.appendChild(this.container.nativeElement, notificationBox)
      this.renderer.appendChild(notificationBox, content)
      
      setTimeout(() => {
        this.renderer.setStyle(notificationBox, 'opacity', '0');
        
        setTimeout(() => {
          this.renderer.removeChild(this.container?.nativeElement, notificationBox) 
        }, notification.duration)
      }, notification.duration)
    }

  }

}
