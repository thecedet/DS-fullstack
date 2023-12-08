import { Component } from '@angular/core';
import { RestaurantTableComponent } from "./restaurant-table/restaurant-table.component";
import { RestaurantFormComponent } from "./restaurant-form/restaurant-form.component";
import { IRestaurantCreate, IRestaurantSummary } from '../../models/restaurant.models';
import { RestaurantService } from '../../services/restaurant.service';
import { NotificationService } from '../../services/notification.service';

@Component({
    selector: 'app-home-page',
    standalone: true,
    templateUrl: './home-page.component.html',
    styleUrl: './home-page.component.css',
    imports: [RestaurantTableComponent, RestaurantFormComponent]
})
export class HomePageComponent {

    public restaurants : IRestaurantSummary[] = [];
    public restaurant  : IRestaurantCreate = {
        addresse: "",
        nom: ""
    }

    constructor(
        private readonly restaurantService : RestaurantService,
        private readonly ntfService : NotificationService
    ) {}

    ngOnInit() : void {
        this.restaurantService.getRestaurants().subscribe({
            next: restaurants => {
                this.restaurants = restaurants;
            },
            error: () => this.ntfService.error("Impossible de récupérer les restaurants")
        })
    }

    public create(restaurant: IRestaurantCreate) : void {
        this.restaurantService.createRestaurant(restaurant).subscribe({
         next: value => {
             this.restaurants.push(value);
             this.ntfService.success("Création du restaurant avec succès")
         },
         error: () => this.ntfService.error("Création du restaurant avec erreur")
        })
     }


}
