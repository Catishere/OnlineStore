import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import { Client } from '@/_models';
import { ClientService, AuthenticationService } from '@/_services';

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent implements OnInit {
    currentClient: Client;
    clients = [];

    constructor(
        private authenticationService: AuthenticationService,
        private clientService: ClientService
    ) {
        this.currentClient = this.authenticationService.currentClientValue;
    }

    ngOnInit() {
        this.loadAllClients();
    }

    deleteClient(id: number) {
        this.clientService.delete(id)
            .pipe(first())
            .subscribe(() => this.loadAllClients());
    }

    private loadAllClients() {
        this.clientService.getAll()
            .pipe(first())
            .subscribe(clients => this.clients = clients);
    }
}