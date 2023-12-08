import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from '../../services/notification.service';
import { RestaurantService } from '../../services/restaurant.service';
import { EvaluationService } from '../../services/evaluation.service';
import { IRestaurant, IRestaurantUpdate } from '../../models/restaurant.models';
import { IMessage } from '../../models/message.models';
import { IEvaluationCreate } from '../../models/evaluation.models';
import { ImageComponent } from "../../components/image/image.component";
import { RestoFormComponent } from "../../components/resto-form/resto-form.component";
import { EvalFormComponent } from "./eval-form/eval-form.component";
import { EvalListComponent } from "./eval-list/eval-list.component";
import { ITag } from '../../models/tag.models';
import { TagService } from '../../services/tag.service';

@Component({
    selector: 'app-detail-page',
    standalone: true,
    templateUrl: './detail-page.component.html',
    styleUrl: './detail-page.component.css',
    imports: [ImageComponent, RestoFormComponent, EvalFormComponent, EvalListComponent]
})
export class DetailPageComponent {

  private id : string = this.activatedRoute.snapshot.params["id"]

  restaurant ?: IRestaurant;
  restaurantUpdate ?: IRestaurant;
  image ?: IMessage;
  evaluation : IEvaluationCreate = {
    commentaire: "",
    date: new Date(),
    nom: "",
    note: 0
  };
  public tags ?: ITag[];

  constructor(
    private readonly restaurantService : RestaurantService,
    private readonly activatedRoute : ActivatedRoute,
    private readonly ntfService : NotificationService,
    private readonly router : Router,
    private readonly tagService : TagService
  ) {}

  ngOnInit() : void {
    this.tagService.getTags().subscribe({
      next: tags => {
          this.tags = tags;
      },
      error: () => this.ntfService.error("Impossible de récupérer les tags")
  })
    this.getRestaurant(this.id);
  }

  private getRestaurant(id : string) : void {

    this.restaurantService.getRestaurant(id).subscribe({
      next: (restaurant : IRestaurant) => {
        this.restaurant = restaurant;
        this.restaurantUpdate = Object.assign({}, restaurant);
        this.getImage();
      },
      error: () => this.router.navigate([`404`])
    })

  }

  private getImage() : void {
    this.restaurantService.getPresignedUrl(Number(this.id)).subscribe((message : IMessage) => {
        this.image = message;
    })
  }

  public uploadImage(file : File) : void {
    this.restaurantService.uploadImage(this.id, file).subscribe((response : IMessage) => {
        if(response === null) {
            this.ntfService.success("upload succès")
            this.getImage()
            this.image = response;
        }else this.ntfService.error("Impossible d'upload l'image")
    })
  }

  public uploadEvaluation(evaluation : IEvaluationCreate) {
    this.restaurantService.createEvaluation(this.id, evaluation).subscribe({
      next: value => {
        this.getRestaurant(this.id);
        this.ntfService.success("Ajout de l'evaluation")
      },
      error: () => this.ntfService.error("Impossible de mettre l'evaluation")
    })
  }

  public updateRestaurant(restaurant : IRestaurantUpdate) {
    this.restaurantService.updateRestaurant(this.id, restaurant).subscribe({
      next: () => {
        this.ntfService.success("Update du restaurant avec succès")
        this.restaurant = Object.assign({}, this.restaurantUpdate)
      },
      error: () => this.ntfService.error("Update du restaurant avec erreur")
    })
  }

}
