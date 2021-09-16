package com.raystatic.expensemanagercompose.presentation.home;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0007\u0010\b\u001a\u001e\u0010\t\u001a\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0007\u001a.\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u0014H\u0007\u001aA\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00192\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0014H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001a\u0010\u001b\u001a\u001a\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u001a\u0010\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020!H\u0007\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\""}, d2 = {"CircularProgressBar", "", "percentage", "", "variantColor", "Landroidx/compose/ui/graphics/Color;", "color", "CircularProgressBar-WkMS-hQ", "(FJJ)V", "DurationSelector", "durationSelectors", "", "Lcom/raystatic/expensemanagercompose/ui/screens/home/DurationSelector;", "vm", "Lcom/raystatic/expensemanagercompose/ui/screens/home/HomeViewModel;", "DurationSelectorItem", "durationSelector", "modifier", "Landroidx/compose/ui/Modifier;", "onClick", "Lkotlin/Function1;", "ExpensesItem", "backgroundColor", "textColor", "expensesItem", "Lcom/raystatic/expensemanagercompose/ui/screens/home/ExpensesItem;", "ExpensesItem-RFnl5yQ", "(JJLcom/raystatic/expensemanagercompose/ui/screens/home/ExpensesItem;Lkotlin/jvm/functions/Function1;)V", "HomeScreen", "navController", "Landroidx/navigation/NavController;", "WelcomeCard", "userName", "", "presentation_debug"})
public final class HomeScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void HomeScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.raystatic.expensemanagercompose.presentation.home.HomeViewModel vm) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DurationSelector(@org.jetbrains.annotations.NotNull()
    java.util.List<com.raystatic.expensemanagercompose.presentation.home.DurationSelector> durationSelectors, @org.jetbrains.annotations.NotNull()
    com.raystatic.expensemanagercompose.presentation.home.HomeViewModel vm) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DurationSelectorItem(@org.jetbrains.annotations.NotNull()
    com.raystatic.expensemanagercompose.presentation.home.DurationSelector durationSelector, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.raystatic.expensemanagercompose.presentation.home.DurationSelector, kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void WelcomeCard(@org.jetbrains.annotations.NotNull()
    java.lang.String userName) {
    }
}