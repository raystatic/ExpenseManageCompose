package com.raystatic.expensemanagercompose.presentation.home;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B@\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\u0019\u0010\u001c\u001a\u00020\tH\u00c6\u0003\u00f8\u0001\u0000\u00f8\u0001\u0002\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001d\u0010\u0010J\u0019\u0010\u001e\u001a\u00020\tH\u00c6\u0003\u00f8\u0001\u0000\u00f8\u0001\u0002\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001f\u0010\u0010J\u0019\u0010 \u001a\u00020\tH\u00c6\u0003\u00f8\u0001\u0000\u00f8\u0001\u0002\u00f8\u0001\u0001\u00a2\u0006\u0004\b!\u0010\u0010J\\\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\tH\u00c6\u0001\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b#\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010(\u001a\u00020)H\u00d6\u0001J\t\u0010*\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\b\u001a\u00020\t\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u000b\u001a\u00020\t\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u001c\u0010\n\u001a\u00020\t\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0017\u0010\u0010\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b!\u00a8\u0006+"}, d2 = {"Lcom/raystatic/expensemanagercompose/ui/screens/home/ExpensesItem;", "", "title", "", "percentage", "", "fromDate", "amount", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "variantColor", "highlightColor", "(Ljava/lang/String;FLjava/lang/String;FJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAmount", "()F", "getBackgroundColor-0d7_KjU", "()J", "J", "getFromDate", "()Ljava/lang/String;", "getHighlightColor-0d7_KjU", "getPercentage", "getTitle", "getVariantColor-0d7_KjU", "component1", "component2", "component3", "component4", "component5", "component5-0d7_KjU", "component6", "component6-0d7_KjU", "component7", "component7-0d7_KjU", "copy", "copy-Uzs9YN0", "(Ljava/lang/String;FLjava/lang/String;FJJJ)Lcom/raystatic/expensemanagercompose/ui/screens/home/ExpensesItem;", "equals", "", "other", "hashCode", "", "toString", "presentation_debug"})
public final class ExpensesItem {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String title = null;
    private final float percentage = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fromDate = null;
    private final float amount = 0.0F;
    private final long backgroundColor = 0L;
    private final long variantColor = 0L;
    private final long highlightColor = 0L;
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    private ExpensesItem(java.lang.String title, float percentage, java.lang.String fromDate, float amount, long backgroundColor, long variantColor, long highlightColor) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    public final float component2() {
        return 0.0F;
    }
    
    public final float getPercentage() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFromDate() {
        return null;
    }
    
    public final float component4() {
        return 0.0F;
    }
    
    public final float getAmount() {
        return 0.0F;
    }
}