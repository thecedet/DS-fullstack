import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { IRestaurantCreate } from '../../../models/restaurant.models';

@Component({
  selector: 'app-restaurant-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './restaurant-form.component.html',
  styleUrl: './restaurant-form.component.css'
})
export class RestaurantFormComponent {

  @Input() public restaurant ?: IRestaurantCreate;
  @Output() public create : EventEmitter<IRestaurantCreate> = new EventEmitter();

  public submit(form : NgForm) : void {
    if(form.valid) {
      this.create.emit(this.restaurant)
      form.reset(this.restaurant)
    }
  }

}
