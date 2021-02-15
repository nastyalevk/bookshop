import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BoardAdminComponent } from './component/board-admin/board-admin.component';
import { BoardClientComponent } from './component/board-client/board-client.component';
import { BoardOwnerComponent } from './component/ownerFunctionality/board-owner/board-owner.component';
import { HomeComponent } from './component/home/home.component';
import { LoginComponent } from './component/auth/login/login.component';
import { ProfileComponent } from './component/profile/profile.component';
import { RegisterComponent } from './component/auth/register/register.component';
import { UserComponent } from './component/user/user.component';
import { UserFormComponent } from './component/user-form/user-form.component';
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
import { ShopPersonalPageComponent } from './component/shop-personal-page/shop-personal-page.component';
import { ShopReviewComponent } from './component/shop-review/shop-review.component';
import { TokenVerificationComponent } from './component/token-verification/token-verification.component';
import { BookCommentApproveComponent } from './component/comment-approve/book-comment-approve/book-comment-approve.component';
import { ShopCommentApproveComponent } from './component/comment-approve/shop-comment-approve/shop-comment-approve.component';
import { EditCommentComponent } from './component/comment-approve/edit-comment/edit-comment.component';
import { NotExistComponent } from './component/not-exist/not-exist.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'client', component: BoardClientComponent},
  { path: 'owner', component: BoardOwnerComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: 'edit-user/:id', component: UserComponent },
  { path: 'new', component: UserFormComponent },
  { path: 'book/:id', component: BookComponent },
  { path: 'cart', component: CartComponent },
  { path: 'order', component: OrderComponent },
  { path: 'order-info', component: OrderSubmitComponent },
  { path: 'orders/shop/:id', component: OrdersByShopComponent },
  { path: 'shop/edit/:id', component: EditShopComponent},
  { path: 'shop/newBook/:id', component: NewBookComponent},
  { path: 'shop/addBooks/:id', component: AllBooksOwnerComponent},
  { path: 'shop/addBook/:shopId/:bookId', component: AddExistentBookInShopComponent},
  { path: 'shop/:id', component: ShopAssortmentComponent},
  { path: 'shop/:shopId/book/:bookId', component: BookPersonalPageInShopComponent},
  { path: 'new/shop', component: CreateNewShopComponent },
  { path: 'order/edit/:orderId', component: EditOrderComponent },
  { path: 'shop/page/:shopId', component: ShopPersonalPageComponent },
  { path: 'shop/:shopId/review', component: ShopReviewComponent},
  { path: 'auth/:token', component: TokenVerificationComponent},
  { path: 'admin/book/comments', component: BookCommentApproveComponent},
  { path: 'admin/shop/comments', component: ShopCommentApproveComponent},
  { path: 'review/:reviewId/shop/:shopId', component: EditCommentComponent},
  { path: 'review/:reviewId/book/:bookId', component: EditCommentComponent},
  { path: 'error', component: NotExistComponent},
  { path: '**', redirectTo: 'home', pathMatch: 'full' },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }