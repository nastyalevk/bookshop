import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/auth/login/login.component';
import { RegisterComponent } from './component/auth/register/register.component';
import { HomeComponent } from './component/home/home.component';
import { ProfileComponent } from './component/profile/profile.component';
import { BoardAdminComponent } from './component/board-admin/board-admin.component';
import { BoardOwnerComponent } from './component/ownerFunctionality/board-owner/board-owner.component';
import { authInterceptorProviders } from './helpers/auth.interceptor';
import { AgGridModule } from 'ag-grid-angular';
import { UserComponent } from './component/user/user.component';
import { UserFormComponent } from './component/user-form/user-form.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { BookComponent } from './component/book/book.component';
import { CartComponent } from './component/cart/cart.component';
import { OrderComponent } from './component/order/order.component';
import { OrderSubmitComponent } from './component/order-submit/order-submit.component';
import { OrdersByShopComponent } from './component/ownerFunctionality/orders-by-shop/orders-by-shop.component';
import { EditShopComponent } from './component/ownerFunctionality/edit-shop/edit-shop.component';
import { NewBookComponent } from './component/ownerFunctionality/new-book/new-book.component';
import { AllBooksOwnerComponent } from './component/ownerFunctionality/all-books-owner/all-books-owner.component';
import { AddExistentBookInShopComponent } from './component/ownerFunctionality/add-existent-book-in-shop/add-existent-book-in-shop.component';
import { ShopAssortmentComponent } from './component/ownerFunctionality/shop-assortment/shop-assortment.component';
import { BookPersonalPageInShopComponent } from './component/ownerFunctionality/book-personal-page-in-shop/book-personal-page-in-shop.component';
import { CreateNewShopComponent } from './component/ownerFunctionality/create-new-shop/create-new-shop.component';
import { EditOrderComponent } from './component/ownerFunctionality/edit-order/edit-order.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ShopPersonalPageComponent } from './component/shop-personal-page/shop-personal-page.component';
import { ShopReviewComponent } from './component/shop-review/shop-review.component';
import { BoardClientComponent } from './component/board-client/board-client.component';
import { TokenVerificationComponent } from './component/token-verification/token-verification.component';
import { BookCommentApproveComponent } from './component/comment-approve/book-comment-approve/book-comment-approve.component';
import { ShopCommentApproveComponent } from './component/comment-approve/shop-comment-approve/shop-comment-approve.component';
import { EditCommentComponent } from './component/comment-approve/edit-comment/edit-comment.component';
import { NotExistComponent } from './component/not-exist/not-exist.component';
import { NgbdModalContentComponent } from './component/ngbd-modal-content/ngbd-modal-content.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardAdminComponent,
    BoardOwnerComponent,
    BoardClientComponent,
    UserComponent,
    UserFormComponent,
    BookComponent,
    CartComponent,
    OrderComponent,
    OrderSubmitComponent,
    OrdersByShopComponent,
    EditShopComponent,
    NewBookComponent,
    AllBooksOwnerComponent,
    AddExistentBookInShopComponent,
    ShopAssortmentComponent,
    BookPersonalPageInShopComponent,
    CreateNewShopComponent,
    EditOrderComponent,
    ShopPersonalPageComponent,
    ShopReviewComponent,
    TokenVerificationComponent,
    BookCommentApproveComponent,
    ShopCommentApproveComponent,
    EditCommentComponent,
    NotExistComponent,
    NgbdModalContentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AgGridModule.withComponents([]),
    NgxPaginationModule, 
    NgbModule
  ],
  providers: [
    authInterceptorProviders,
    BoardAdminComponent,
    HomeComponent,
    CartComponent,
    BoardClientComponent,
    AddExistentBookInShopComponent,
    BookComponent,
    AllBooksOwnerComponent
  ],
  bootstrap: [AppComponent, BoardAdminComponent]
})
export class AppModule { }