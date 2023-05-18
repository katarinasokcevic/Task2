import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface IRentable {
  String description();

  double calculatePrice();

}

class Video implements IRentable {
  private final double baseVideoPrice = 9.99;
  public String name;

  public Video(String name) {
    this.name = name;
  }

  public String description() {
    return getName();
  }

  public double calculatePrice() {
    return baseVideoPrice;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

class RentingConsolePrinter {
  public void PrintTotalPrice(List<IRentable> items) {
    double totalPrice = 0;
    for (IRentable item : items) {
      totalPrice += item.calculatePrice();
    }
    System.out.println(totalPrice);
  }

  public void displayItems(List<IRentable> items) {
    for (IRentable item : items) {
      System.out.println(item.description());
    }
  }
}

class Book implements IRentable {
  private final double baseBookPrice = 3.99;
  public String name;

  public Book(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String description() {
    return getName();
  }

  public double calculatePrice() {
    return baseBookPrice;
  }
}

abstract class RentableDecorator implements IRentable {
  private IRentable rentable;

  public RentableDecorator(IRentable rentable) {
    this.rentable = rentable;
  }

  public double calculatePrice() {
    return rentable.calculatePrice();
  }

  public String description() {
    return rentable.description();
  }

}

class HotItem extends RentableDecorator {
  private final double HotItemBonus = 1.99;

  public HotItem(IRentable rentable) {
    super(rentable);
  }

  public double calculatePrice() {
    return super.calculatePrice() + this.HotItemBonus;
  }

  public String description() {
    return "Trending: " + super.description() + "!!!!";
  }
}

class DiscountedItem extends RentableDecorator {
  private int discount = 0;

  public DiscountedItem(IRentable rentable, int discount) {
    super(rentable);
    this.discount = discount;
  }

  public double calculatePrice() {
    return super.calculatePrice() - this.discount * super.calculatePrice();
  }

  public String description() {
    return "~~~~" + super.description() + " now at: " + this.discount + " % off~~~~";
  }
}

public class Main {
  public static void main(String[] args) {
    IRentable book = new Book("Mali princ");
    IRentable video = new Video("Funny video");
    IRentable hotItem = new HotItem(book);
    IRentable hotItem2 = new HotItem(video);
    IRentable saleBook = new DiscountedItem(book, 10);
    IRentable saleVideo = new DiscountedItem(video, 30);
    IRentable saleHotBook = new DiscountedItem(hotItem, 20);
    IRentable saleHotVideo = new DiscountedItem(hotItem2, 20);

    RentingConsolePrinter printer = new RentingConsolePrinter();
    List<IRentable> items = new ArrayList<>();
    List<IRentable> sale = new ArrayList<>();

    items.addAll(Arrays.asList(book, video, hotItem, hotItem2));

    System.out.println("U trgovini se nalazi: ");
    printer.displayItems(items);

    System.out.println();

    sale.addAll(Arrays.asList(saleBook, saleVideo, saleHotBook, saleHotVideo));
    
    System.out.println("U trgovini na snizenju se nalazi: ");
    printer.displayItems(sale);

  }
}
