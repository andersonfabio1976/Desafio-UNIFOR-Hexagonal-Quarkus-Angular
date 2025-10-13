import { Injectable } from '@angular/core';
import { RoleGuard } from './role.guard';

@Injectable({ providedIn: 'root' })
export class AdminGuard extends RoleGuard {}
