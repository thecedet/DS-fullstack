import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { NotificationService } from '../../../services/notification.service';
import { IRestaurantSummary } from '../../../models/restaurant.models';
import { CommonModule } from '@angular/common';
import { TableSetColorDirective } from '../../../directives/table-set-color.directive';

@Component({
  selector: 'app-restaurant-table',
  standalone: true,
  imports: [CommonModule, TableSetColorDirective],
  templateUrl: './restaurant-table.component.html',
  styleUrl: './restaurant-table.component.css'
})
export class RestaurantTableComponent {
  
  constructor(
    private readonly router: Router
  ) {}

  navigateToDetails(id: number) {
    this.router.navigate([`restaurants/${id}`])
  }

  @Input() public restaurants ?: IRestaurantSummary[];

}
