import { CommonModule } from '@angular/common';
import { Component, ElementRef, EventEmitter, Output } from '@angular/core';
import { NotificationService } from '../../services/notification.service';

@Component({
  selector: 'app-input-file',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './input-file.component.html',
  styleUrl: './input-file.component.css'
})
export class InputFileComponent {

  public file ?: File;

  @Output() public uploadFile : EventEmitter<File> = new EventEmitter();

  //constructor(private ntfService : NotificationService) {}

  public setState(state : boolean) : void {
    if(state) {
      //this.ntfService.success("L'image s'est bien téléversée");
    }else {
      //this.ntfService.error("Erreur pendant le téléversement");
    }
  }
  
  public onChange(event : Event) {
    const element = event.target as HTMLInputElement;
    if(element.files) {
      this.file = element.files[0]
    }
    
  }

  public onUpload(input : HTMLInputElement) : void {
    if(this.file) {
      //this.ntfService.warning("Téléversement en cours de l'image")
      this.uploadFile.emit(this.file)
      this.file = undefined;
      input.value = '';
    }
  }

}
