import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Lease } from '../models/lease.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class LeaseService {
  private baseUrl = '/api/leases';

  constructor(private http: HttpClient) {}

  listLeases(): Observable<Lease[]> {
    return this.http.get<Lease[]>(this.baseUrl);
  }

  createLease(lease: Partial<Lease>): Observable<any> {
    return this.http.post(this.baseUrl, lease);
  }

  returnLease(leaseId: number): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/${leaseId}/return`,{});
  }
}
