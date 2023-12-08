import { Routes } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { NotfoundPageComponent } from './pages/notfound-page/notfound-page.component';
import { DetailPageComponent } from './pages/detail-page/detail-page.component';

export const routes: Routes = [
    {path: "", component: HomePageComponent},
    {path: "restaurants/:id", component: DetailPageComponent},
    {path: "404", component: NotfoundPageComponent},
    {path: "**", redirectTo: "/404"}
];
