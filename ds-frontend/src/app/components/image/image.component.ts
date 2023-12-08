import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IMessage } from '../../models/message.models';
import { NotificationService } from '../../services/notification.service';
import { CommonModule } from '@angular/common';
import { InputFileComponent } from "../input-file/input-file.component";

@Component({
    selector: 'app-image',
    standalone: true,
    templateUrl: './image.component.html',
    styleUrl: './image.component.css',
    imports: [CommonModule, InputFileComponent]
})
export class ImageComponent {

  @Input() public image ?: IMessage | undefined;

  @Output() public uploadImage : EventEmitter<File> = new EventEmitter();

  constructor(
    private readonly ntfService : NotificationService
  ) {}

  public onErrorImage() : void {
      this.ntfService.error("Impossible de charger les images")
  }
}
