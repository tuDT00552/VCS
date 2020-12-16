export interface AccountModel {
	id?: number;
    account_number?: number;
    firstname?: string;
	lastname?: string;
	address?: string;
	balance?: number;
	gender?: string;
	city?: string;
	employer?: string;
	state?: string;
	age?: number;
	email?: string;
}

export interface AccountModelItem {
    content: AccountModel[];
    totalElements: number;
    totalPages: number;
	pageable: pageable;
}

export interface pageable {
    pageNumber: number;
    pageSize: number;
}