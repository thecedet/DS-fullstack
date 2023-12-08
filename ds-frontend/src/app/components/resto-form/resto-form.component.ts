import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';
import { IRestaurantUpdate } from '../../models/restaurant.models';
import { ITag } from '../../models/tag.models';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-resto-form',
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatSelectModule, ReactiveFormsModule],
  templateUrl: './resto-form.component.html',
  styleUrl: './resto-form.component.css'
})
export class RestoFormComponent {

  @Input() restaurant ?: IRestaurantUpdate
  @Output() public updateActeur : EventEmitter<IRestaurantUpdate> = new EventEmitter();
  @Input() public tags ?: ITag[];


  public submit(form : NgForm) : void {
    if(form.valid) {
      this.updateActeur.emit(this.restaurant)
      form.reset(this.restaurant)
    }
  }

  public onChangeTag(event : ITag[]) {
    if(this.restaurant) this.restaurant.tags = event
  }

}
