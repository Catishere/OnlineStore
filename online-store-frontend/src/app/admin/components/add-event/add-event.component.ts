import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Event, EventService} from "../../../store";

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.scss']
})
export class AddEventComponent implements OnInit {

  public addEventForm: FormGroup;
  public alert_message: string;
  public alert_status: string;

  constructor(
    private router: Router, fb: FormBuilder,
    private eventService: EventService
  ) {
    this.addEventForm = fb.group({
      image: ['', Validators.required],
      name: ['', Validators.required],
    });
  }
  ngOnInit() {
  }

  async onSubmit() {
    let event: Event = await this.eventService.add(this.addEventForm.value);
    if (event.id == null) {
      this.alert_status = "error";
      this.alert_message = "This event already exists!";
    }
    else {
      this.alert_status = "success";
      this.alert_message = "Event added!";
    }
    this.addEventForm.reset();
  }
}
