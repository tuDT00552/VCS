export interface IGridCell {
  label: string;
  align: 'center' | 'left' | 'right';
  field: string;
  width?: number;
}
