import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css'] // Correction ici
})
export class OrderDetailsComponent implements OnInit {
  orderId!: string;
  orderDetails: any;

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    // Récupération de l'ID depuis les paramètres de route
    this.orderId = this.route.snapshot.params['id'];

    // Construction de l'URL avec l'ID
    const url = `http://localhost:8086/api/orders/${this.orderId}`;

    // Appel HTTP pour récupérer les détails de la commande
    this.http.get(url).subscribe({
      next: (order) => {
        this.orderDetails = order;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des détails de la commande :', err);
      }
    });
  }

  getTotal(orderDetails: any) {
    let total: number=0;
    for (let pi of orderDetails.productItems){
      total=total +(pi.prix*pi.quantity);
    }
    return total;
  }
  onBack(): void {
    // Navigation arrière
    window.history.back();
  }
}
