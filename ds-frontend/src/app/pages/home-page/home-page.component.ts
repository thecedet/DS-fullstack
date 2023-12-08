import { Component } from '@angular/core';
import { RestaurantTableComponent } from "./restaurant-table/restaurant-table.component";
import { RestaurantFormComponent } from "./restaurant-form/restaurant-form.component";
import { IRestaurantCreate, IRestaurantSummary, IRestaurant } from '../../models/restaurant.models';
import { RestaurantService } from '../../services/restaurant.service';
import { NotificationService } from '../../services/notification.service';
import { ITag } from '../../models/tag.models';
import { TagService } from '../../services/tag.service';
import { Observable } from 'rxjs';

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
    public tags ?: ITag[];

    constructor(
        private readonly restaurantService : RestaurantService,
        private readonly ntfService : NotificationService,
        private readonly tagService : TagService
    ) {}

    ngOnInit() : void {
        this.tagService.getTags().subscribe({
            next: tags => {
                this.tags = tags;
            },
            error: () => this.ntfService.error("Impossible de récupérer les tags")
        })
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
            if(restaurant.tags) {
                this.restaurantService.addTag(value.id, restaurant.tags).subscribe({

                })
            }
            this.restaurants.push(value);
            this.ntfService.success("Création du restaurant avec succès")
         },
         error: () => this.ntfService.error("Création du restaurant avec erreur")
        })
    }

    


}
