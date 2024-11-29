package backEnd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class GameShopItems {

	public String itemID, itemName, itemRarity, itemDescription;
	public int itemCoinPrice, itemGemPrice, itemSellingPrice;
	public int addCoin, coinMultiplier, gemDropLuck, itemDropLuck, gachaLuck, passiveIncome;
	
	public GameShopItems(String itemID, String itemName, String itemRarity,
			String itemCoinPrice, String itemGemPrice, String itemSellingPrice,
			String addCoin, String coinMultiplier, String gemDropLuck, String itemDropLuck,
			String gachaLuck, String passiveIncome, String itemDescription) {
		
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemRarity = itemRarity;
		
		this.itemCoinPrice = Integer.parseInt(itemCoinPrice);
		this.itemGemPrice = Integer.parseInt(itemGemPrice);
		this.itemSellingPrice = Integer.parseInt(itemSellingPrice);
		
		this.addCoin = Integer.parseInt(addCoin);
		this.coinMultiplier = Integer.parseInt(coinMultiplier);
		this.gemDropLuck = Integer.parseInt(gemDropLuck);
		this.itemDropLuck = Integer.parseInt(itemDropLuck);
		this.gachaLuck = Integer.parseInt(gachaLuck);
		this.passiveIncome = Integer.parseInt(passiveIncome);
		
		this.itemDescription = itemDescription;		
	}
	
	public static HashMap<String, GameShopItems> loadDefaultShop() {
		String filePath = ".//resource//shopItems.json";
		HashMap<String, GameShopItems> shopItems = new HashMap<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			
			while((line = br.readLine()) != null) {
				String[] values = line.split("\t");
				
				String id = values[0];
				String name = values[1];
				String rarity = values[2];
				String coinPrice = values[3];
				String gemPrice = values[4];
				String sellingPrice = values[5];
				String addCoin = values[6];
				String coinMultiplier = values[7];
				String gemDropLuck = values[8];
				String itemDropLuck = values[9];
				String gachaLuck = values[10];
				String passiveIncome = values[11];
				String description = values[12];
				
				GameShopItems item = new GameShopItems(id, name, rarity,
						coinPrice, gemPrice, sellingPrice,
						addCoin, coinMultiplier, gemDropLuck, itemDropLuck,
						gachaLuck, passiveIncome, description);
				shopItems.put(id, item);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return shopItems;
	}
}
