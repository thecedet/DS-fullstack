import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';
import { IRestaurantCreate } from '../../../models/restaurant.models';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import { ITag } from '../../../models/tag.models';



@Component({
  selector: 'app-restaurant-form',
  standalone: true,
  imports: [CommonModule, MatFormFieldModule, MatSelectModule, FormsModule, ReactiveFormsModule],
  templateUrl: './restaurant-form.component.html',
  styleUrl: './restaurant-form.component.css'
})
export class RestaurantFormComponent {

  @Input() public restaurant ?: IRestaurantCreate;
  @Output() public create : EventEmitter<IRestaurantCreate> = new EventEmitter();
  @Input() public tags ?: ITag[];

  public submit(form : NgForm) : void {
    if(form.valid) {
      this.create.emit(this.restaurant)
      form.reset()
    }
  }

  public onChangeTag(event : ITag[]) {
    if(this.restaurant) this.restaurant.tags = event
  }

}
