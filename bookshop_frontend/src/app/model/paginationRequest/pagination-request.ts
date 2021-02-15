export class PaginationRequest {
    bookName: string | undefined;
    page: number | undefined;
    size: number | undefined;
    sort: Map<string, string> | undefined;
}
