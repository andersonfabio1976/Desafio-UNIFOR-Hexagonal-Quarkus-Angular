export interface SemestreDTO {
  identifier?: number;
  numero: number;
  curso?: { identifier?: number; nome?: string };
}
