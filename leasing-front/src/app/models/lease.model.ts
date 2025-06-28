import { Car } from '../models/car.model';
import { Customer } from '../models/customer.model';

export interface Lease {
  id?: number;
  customer: Customer;
  car: Car;
  startDate: string;
  endDate: string;
  actualReturnDate?: string;
}