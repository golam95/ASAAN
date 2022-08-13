package com.biit.asaan.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.biit.asaan.model.BuyingProduct;
import com.biit.asaan.model.ContactInfo;
import com.biit.asaan.model.FarmerSchedule;
import com.biit.asaan.model.Order;
import com.biit.asaan.model.ProdutCategory;
import com.biit.asaan.model.SellingMsg;
import com.biit.asaan.model.SellingProduct;
import com.biit.asaan.model.User;
import com.biit.asaan.service.BuyingProductService;
import com.biit.asaan.service.ContactInfoService;
import com.biit.asaan.service.FarmerScheduleService;
import com.biit.asaan.service.OrderService;
import com.biit.asaan.service.ProductCategoryService;
import com.biit.asaan.service.SellingMsgService;
import com.biit.asaan.service.SellingProductService;
import com.biit.asaan.service.UserService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {

	@Autowired
	private ProductCategoryService productcategoryService;

	@Autowired
	private BuyingProductService buyproductService;

	@Autowired
	private SellingProductService sellproductService;

	@Autowired
	private UserService userService;

	@Autowired
	private ContactInfoService contactinfoService;

	@Autowired
	private FarmerScheduleService farmerScheduleService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private SellingMsgService sellingMsgService;

	@GetMapping("/adminIndex")
	public String adminhomeFrom(Model model) {
		model.addAttribute("buyCount", buyproductService.count());
		model.addAttribute("sellCount", sellproductService.count());

		model.addAttribute("SellMsgCount", sellingMsgService.count());
		model.addAttribute("userCount", userService.count());
		model.addAttribute("orderCount", orderService.count());

		model.addAttribute("contactCount", contactinfoService.count());
		model.addAttribute("scheduleCount", farmerScheduleService.count());

		return "/admin/adminIndex";
	}

	@GetMapping("/user-setup")
	public String userSetupFrom(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("userList", userService.findByrolename("ROLE_ADMIN"));
		return "/admin/user-setup";
	}

	@PostMapping("/addUser")
	public String addUser(@ModelAttribute("user") User user, Model model) {

		User checkName = userService.findByUserName(user.getUserName());

		User checkEmail = userService.findByemail(user.getEmail());

		User checkPhone = userService.findByphone(user.getPhone());

		if (checkName != null) {
			model.addAttribute("user", new User());
			model.addAttribute("errorMsg", "Username already exists.");
			model.addAttribute("userList", userService.findByrolename("ROLE_ADMIN"));
			return "/admin/user-setup";
		}
		if (checkEmail != null) {
			model.addAttribute("user", new User());
			model.addAttribute("errorMsg", "Email address already exist!!");
			model.addAttribute("userList", userService.findByrolename("ROLE_ADMIN"));
			return "/admin/user-setup";
		}
		if (checkPhone != null) {
			model.addAttribute("user", new User());
			model.addAttribute("errorMsg", "Contact no already exist!!");
			model.addAttribute("userList", userService.findByrolename("ROLE_ADMIN"));
			return "/admin/user-setup";
		}
		userService.saveUser(user);
		model.addAttribute("successMsg", "Add successfully!!");
		model.addAttribute("user", new User());
		model.addAttribute("userList", userService.findByrolename("ROLE_ADMIN"));
		return "/admin/user-setup";
	}

	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("id") Long id) {
		userService.deleteUser(id);
		return "redirect:/admin/user-setup";
	}

	@GetMapping("/report")
	public String generateReport() {
		return "/admin/report";
	}

	@GetMapping("/adminViewuser")
	public String viewUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("userList", userService.findByrolename("ROLE_USER"));
		return "/admin/adminViewuser";
	}

	@GetMapping("/productCategory")
	public String productCategory(Model model) {
		model.addAttribute("category", new ProdutCategory());
		model.addAttribute("categoryList", productcategoryService.getList());
		return "/admin/category-setup";
	}

	@PostMapping("/addProductCategory")
	public String addProductCategory(@ModelAttribute("category") ProdutCategory productCategory, Model model) {

		ProdutCategory checkCatName = productcategoryService.findBycategoryName(productCategory.getCategoryName());

		if (checkCatName != null) {
			model.addAttribute("errorMsg", "Category name already exist!!");
			model.addAttribute("category", new ProdutCategory());
			model.addAttribute("categoryList", productcategoryService.getList());
			return "/admin/category-setup";
		}
		productcategoryService.addCategory(productCategory);
		model.addAttribute("successMsg", "Add successfully!!");
		model.addAttribute("category", new ProdutCategory());
		model.addAttribute("categoryList", productcategoryService.getList());
		return "/admin/category-setup";
	}

	@GetMapping("/deleteProductCategory")
	public String deleteProductCategory(@RequestParam("id") Long id) {
		productcategoryService.deleteProductCategory(id);
		return "redirect:/admin/productCategory";
	}

	@GetMapping("/buyProduct")
	public String buyProduct(Model model) {
		model.addAttribute("buyProduct", new BuyingProduct());
		model.addAttribute("categoryList", productcategoryService.getList());
		model.addAttribute("buyProductList", buyproductService.getbuyProductList());
		return "/admin/buy-Product-setup";
	}

	@PostMapping("/addBuyProduct")
	public String addBuyProduct(@ModelAttribute("buyProduct") BuyingProduct buyproduct, Model model) {

		BuyingProduct checkproductName = buyproductService.findByproductName(buyproduct.getProductName());

		if (checkproductName != null) {
			model.addAttribute("errorMsg", "Product name already exist!!");
			model.addAttribute("buyProduct", new BuyingProduct());
			model.addAttribute("categoryList", productcategoryService.getList());
			model.addAttribute("buyProductList", buyproductService.getbuyProductList());
			return "/admin/buy-Product-setup";
		}
		buyproductService.save(buyproduct);
		model.addAttribute("successMsg", "Add successfully!!");
		model.addAttribute("buyProduct", new BuyingProduct());
		model.addAttribute("categoryList", productcategoryService.getList());
		model.addAttribute("buyProductList", buyproductService.getbuyProductList());
		return "/admin/buy-Product-setup";
	}

	@GetMapping("/deleteBuyProduct")
	public String deleteBuyProduct(@RequestParam("id") Long id) {
		buyproductService.deleteBuyProduct(id);
		return "redirect:/admin/buyProduct";
	}

	@GetMapping("/sellProduct")
	public String sellProduct(Model model) {
		model.addAttribute("sellProduct", new SellingProduct());
		model.addAttribute("categoryList", productcategoryService.getList());
		model.addAttribute("sellProductList", sellproductService.getSellProductList());
		return "/admin/sell-Product-setup";
	}

	@PostMapping("/addSellProduct")
	public String addSellProduct(@ModelAttribute("sellProduct") SellingProduct sellproduct, Model model) {

		SellingProduct checkproductName = sellproductService.findByproductName(sellproduct.getProductName());

		if (checkproductName != null) {
			model.addAttribute("errorMsg", "Product name already exist!!");
			model.addAttribute("sellProduct", new SellingProduct());
			model.addAttribute("categoryList", productcategoryService.getList());
			model.addAttribute("sellProductList", sellproductService.getSellProductList());
			return "/admin/sell-Product-setup";
		}
		sellproductService.save(sellproduct);
		model.addAttribute("successMsg", "Add successfully!!");
		model.addAttribute("sellProduct", new SellingProduct());
		model.addAttribute("categoryList", productcategoryService.getList());
		model.addAttribute("sellProductList", sellproductService.getSellProductList());
		return "/admin/sell-Product-setup";
	}

	@GetMapping("/deleteSellProduct")
	public String deleteSellProduct(@RequestParam("id") Long id) {
		sellproductService.deleteSellingProduct(id);
		return "redirect:/admin/sellProduct";
	}

	@GetMapping("/contactInfo")
	public String contactInfoFrom(Model model) {
		model.addAttribute("contactInfoList", contactinfoService.getList());
		return "/admin/view-contactInfo";
	}

	@GetMapping("/deleteContact")
	public String deleteContact(@RequestParam("id") Long id) {
		contactinfoService.deleteContactInfo(id);
		return "redirect:/admin/contactInfo";
	}

	@GetMapping("/sendContactInfo")
	public String sendContactInfo(@RequestParam("id") Long id, Model model) {
		ContactInfo contactInfo = contactinfoService.findByid(id);
		model.addAttribute("contact", contactInfo);
		return "/admin/update-contactInfo";
	}

	@PostMapping("/sendEmailContact")
	public String addUser(@ModelAttribute("contact") ContactInfo contact, Model model) {
		contactinfoService.updateStatus(contact);
		model.addAttribute("successMsg", "Message viewed sucessfully!!");
		model.addAttribute("contactInfoList", contactinfoService.getList());
		return "/admin/view-contactInfo";
	}

	// same as contact info
	@GetMapping("/customerMsg")
	public String customerMsg(Model model) {
		model.addAttribute("sellingMsgList", sellingMsgService.findAll());
		return "/admin/view-customerMsg";
	}

	@GetMapping("/deleteSellMsg")
	public String deleteSellMsg(@RequestParam("id") Long id) {
		sellingMsgService.deleteSellingMsg(id);
		return "redirect:/admin/customerMsg";
	}

	@GetMapping("/sendSellInfo")
	public String sendSellInfo(@RequestParam("id") Long id, Model model) {
		SellingMsg sellMsg = sellingMsgService.findByid(id);
		model.addAttribute("selling", sellMsg);
		return "/admin/update-sellingInfo";
	}

	@PostMapping("/sendEmailSell")
	public String sendEmailSell(@ModelAttribute("selling") SellingMsg selling, Model model) {
		sellingMsgService.updateStatus(selling);
		model.addAttribute("successMsg", "Message viewed sucessfully!!");
		model.addAttribute("sellingMsgList", sellingMsgService.findAll());
		return "/admin/view-customerMsg";
	}
	// end same as contact Info

	// manage order

	@GetMapping("/customerOrder")
	public String customerOrder(Model model) {
		model.addAttribute("customerOrderList", orderService.getOrderList());
		return "/admin/view-customerOrderList";
	}

	@GetMapping("/deleteCustomerOrder")
	public String deleteCustomerOrder(@RequestParam("id") Long id) {
		orderService.deleteOrder(id);
		return "redirect:/admin/customerOrder";
	}

	@GetMapping("/sendcustomerOrderInfo")
	public String sendcustomerOrderInfo(@RequestParam("id") Long id, Model model) {
		Order orderInfo = orderService.findByid(id);
		model.addAttribute("order", orderInfo);
		return "/admin/update-customerOrderList";
	}

	@PostMapping("/sendEmailcustomerOrder")
	public String sendEmailcustomerOrder(@ModelAttribute("order") Order order, Model model) {
		orderService.updateStatus(order);
		model.addAttribute("successMsg", "Order viewed sucessfully!!");
		model.addAttribute("customerOrderList", orderService.getOrderList());
		return "/admin/view-customerOrderList";
	}

	@GetMapping("/farmerScheduleList")
	public String farmerScheduleList(Model model) {
		model.addAttribute("scheduleList", farmerScheduleService.findAll());
		return "/admin/view-farmerScheduleList";
	}

	@GetMapping("/profile")
	public String adminProfile(Model model, Principal principal) {
		if (principal != null) {
			User getUser = userService.findByUserName(principal.getName());
			model.addAttribute("userProfile", getUser);
		} else
			model.addAttribute("userProfile", new User());

		return "/admin/admin-profile";
	}

	// end manage order

	@GetMapping("/Graphreport")
	public String Graphreport(Model model) {
		int dhaka = 0;
		int khulna = 0;
		int barisal = 0;
		int chittagong = 0;
		int mymensingh = 0;
		int rajshahi = 0;
		int sylhet = 0;
		int rangpur = 0;
		for (FarmerSchedule farmer : farmerScheduleService.findAll()) {

			if (farmer.getDivision().equals("Dhaka")) {
				dhaka += 1;
			}
			if (farmer.getDivision().equals("Khulna")) {
				khulna += 1;
			}
			if (farmer.getDivision().equals("Barisal")) {
				barisal += 1;
			}
			if (farmer.getDivision().equals("Chittagong")) {
				chittagong += 1;
			}
			if (farmer.getDivision().equals("Mymensingh")) {
				mymensingh += 1;
			}
			if (farmer.getDivision().equals("Rajshahi")) {
				rajshahi += 1;
			}
			if (farmer.getDivision().equals("Sylhet")) {
				sylhet += 1;
			}
			if (farmer.getDivision().equals("Rangpur")) {
				rangpur += 1;
			}
			model.addAttribute("dhaka", dhaka);
			model.addAttribute("khulna", khulna);
			model.addAttribute("barisal", barisal);
			model.addAttribute("chittagong", chittagong);
			model.addAttribute("mymensingh", mymensingh);
			model.addAttribute("rajshahi", rajshahi);
			model.addAttribute("sylhet", sylhet);
			model.addAttribute("rangpur", rangpur);
			//
			model.addAttribute("buyCount", buyproductService.count());
			model.addAttribute("sellCount", sellproductService.count());
			model.addAttribute("SellMsgCount", sellingMsgService.count());
			model.addAttribute("orderCount", orderService.count());
			model.addAttribute("contactCount", contactinfoService.count());
			model.addAttribute("scheduleCount", farmerScheduleService.count());
		}
		return "/admin/admin-graph-report";
	}

	@GetMapping("/userReport")
	public void userPdf(HttpServletRequest req, HttpServletResponse res) {

		Document document = new Document();
		String datetime = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 23);
		Font font1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
		Font font5 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD);
		Font font6 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL);
		try {
			res.setContentType("application/pdf");
			PdfWriter.getInstance(document, res.getOutputStream());
			document.open();

			Paragraph paragraph = new Paragraph("ASSAN", font);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);

			Paragraph address = new Paragraph("Address:102, Shukrabad,Dhanmondi, Dhaka-1207", font1);
			address.setAlignment(Element.ALIGN_CENTER);
			document.add(address);

			Paragraph phone = new Paragraph("phone:098765432123", font1);
			phone.setAlignment(Element.ALIGN_CENTER);
			document.add(phone);

			Paragraph email = new Paragraph("Email:assan@gmail.com", font1);
			email.setAlignment(Element.ALIGN_CENTER);
			document.add(email);

			Paragraph monthlyReport = new Paragraph("User Report", font1);
			monthlyReport.setAlignment(Element.ALIGN_LEFT);
			document.add(monthlyReport);

			Paragraph pargraphdate = new Paragraph("Date: " + datetime, font1);
			pargraphdate.setAlignment(Element.ALIGN_LEFT);
			document.add(pargraphdate);

			float[] pointColumnWidths = { 15F, 18F, 15F, 15f };
			PdfPTable table = new PdfPTable(pointColumnWidths);
			table.setSpacingBefore(20);
			table.setWidthPercentage(100f);

			PdfPCell c1 = new PdfPCell(new Phrase("Name", font5));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);

			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("UserName", font5));

			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Email", font5));
			c1.setPaddingBottom(7f);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Phone", font5));
			c1.setPaddingBottom(7f);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			table.setHeaderRows(1);
			c1.setPaddingBottom(10f);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);

			for (User user : userService.findByrolename("ROLE_USER")) {

				PdfPCell cell_1 = new PdfPCell(new Phrase(user.getFirstname(), font6));
				cell_1.setPaddingBottom(6f);
				cell_1.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cell_2 = new PdfPCell(new Phrase((user.getUserName()), font6));
				cell_2.setPaddingBottom(6f);
				cell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cell_3 = new PdfPCell(new Phrase((user.getEmail()), font6));
				cell_3.setPaddingBottom(6f);
				cell_3.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cell_4 = new PdfPCell(new Phrase(user.getPhone(), font6));
				cell_4.setPaddingBottom(6f);
				cell_4.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(cell_1);
				table.addCell(cell_2);
				table.addCell(cell_3);
				table.addCell(cell_4);

			}
			document.add(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();

	}

	@GetMapping("/sellerReport")
	public void sellerReport(HttpServletRequest req, HttpServletResponse res) {

		Document document = new Document();
		String datetime = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 23);
		Font font1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
		Font font5 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD);
		Font font6 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL);
		try {
			res.setContentType("application/pdf");
			PdfWriter.getInstance(document, res.getOutputStream());
			document.open();

			Paragraph paragraph = new Paragraph("ASSAN", font);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);

			Paragraph address = new Paragraph("Address:102, Shukrabad,Dhanmondi, Dhaka-1207", font1);
			address.setAlignment(Element.ALIGN_CENTER);
			document.add(address);

			Paragraph phone = new Paragraph("phone:098765432123", font1);
			phone.setAlignment(Element.ALIGN_CENTER);
			document.add(phone);

			Paragraph email = new Paragraph("Email:assan@gmail.com", font1);
			email.setAlignment(Element.ALIGN_CENTER);
			document.add(email);

			Paragraph monthlyReport = new Paragraph("Farmer Appoinment Report", font1);
			monthlyReport.setAlignment(Element.ALIGN_LEFT);
			document.add(monthlyReport);

			Paragraph pargraphdate = new Paragraph("Date: " + datetime, font1);
			pargraphdate.setAlignment(Element.ALIGN_LEFT);
			document.add(pargraphdate);

			float[] pointColumnWidths = { 15F, 18F, 15F, 15f };
			PdfPTable table = new PdfPTable(pointColumnWidths);
			table.setSpacingBefore(20);
			table.setWidthPercentage(100f);

			PdfPCell c1 = new PdfPCell(new Phrase("Name", font5));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);

			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Email", font5));

			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Product", font5));
			c1.setPaddingBottom(7f);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Division", font5));
			c1.setPaddingBottom(7f);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			table.setHeaderRows(1);
			c1.setPaddingBottom(10f);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);

			for (FarmerSchedule farmer : farmerScheduleService.findAll()) {

				PdfPCell cell_1 = new PdfPCell(new Phrase(farmer.getName(), font6));
				cell_1.setPaddingBottom(6f);
				cell_1.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cell_2 = new PdfPCell(new Phrase((farmer.getEmail()), font6));
				cell_2.setPaddingBottom(6f);
				cell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cell_3 = new PdfPCell(new Phrase((farmer.getProductName()), font6));
				cell_3.setPaddingBottom(6f);
				cell_3.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cell_4 = new PdfPCell(new Phrase(farmer.getDivision(), font6));
				cell_4.setPaddingBottom(6f);
				cell_4.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(cell_1);
				table.addCell(cell_2);
				table.addCell(cell_3);
				table.addCell(cell_4);

			}
			document.add(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();

	}

	@GetMapping("/orderReport")
	public void orderReport(HttpServletRequest req, HttpServletResponse res) {

		Document document = new Document();
		String datetime = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 23);
		Font font1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
		Font font5 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD);
		Font font6 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL);
		try {
			res.setContentType("application/pdf");
			PdfWriter.getInstance(document, res.getOutputStream());
			document.open();

			Paragraph paragraph = new Paragraph("ASSAN", font);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);

			Paragraph address = new Paragraph("Address:102, Shukrabad,Dhanmondi, Dhaka-1207", font1);
			address.setAlignment(Element.ALIGN_CENTER);
			document.add(address);

			Paragraph phone = new Paragraph("phone:098765432123", font1);
			phone.setAlignment(Element.ALIGN_CENTER);
			document.add(phone);

			Paragraph email = new Paragraph("Email:assan@gmail.com", font1);
			email.setAlignment(Element.ALIGN_CENTER);
			document.add(email);

			Paragraph monthlyReport = new Paragraph("Customer Order Report", font1);
			monthlyReport.setAlignment(Element.ALIGN_LEFT);
			document.add(monthlyReport);

			Paragraph pargraphdate = new Paragraph("Date: " + datetime, font1);
			pargraphdate.setAlignment(Element.ALIGN_LEFT);
			document.add(pargraphdate);

			float[] pointColumnWidths = { 15F, 18F, 15F, 15f };
			PdfPTable table = new PdfPTable(pointColumnWidths);
			table.setSpacingBefore(20);
			table.setWidthPercentage(100f);

			PdfPCell c1 = new PdfPCell(new Phrase("Name", font5));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);

			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Product", font5));

			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Price", font5));
			c1.setPaddingBottom(7f);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Quantity", font5));
			c1.setPaddingBottom(7f);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			table.setHeaderRows(1);
			c1.setPaddingBottom(10f);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);

			for (Order order : orderService.getOrderList()) {

				PdfPCell cell_1 = new PdfPCell(new Phrase(order.getUserName(), font6));
				cell_1.setPaddingBottom(6f);
				cell_1.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cell_2 = new PdfPCell(new Phrase((order.getProductName()), font6));
				cell_2.setPaddingBottom(6f);
				cell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cell_3 = new PdfPCell(new Phrase((Double.toString(order.getProductPrice())), font6));
				cell_3.setPaddingBottom(6f);
				cell_3.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cell_4 = new PdfPCell(new Phrase(Integer.toString(order.getQuantity())));
				cell_4.setPaddingBottom(6f);
				cell_4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell_1);
				table.addCell(cell_2);
				table.addCell(cell_3);
				table.addCell(cell_4);

			}
			document.add(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
	}

	@GetMapping("/contactReport")
	public void contactReport(HttpServletRequest req, HttpServletResponse res) {

		Document document = new Document();
		String datetime = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 23);
		Font font1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
		Font font5 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD);
		Font font6 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL);
		try {
			res.setContentType("application/pdf");
			PdfWriter.getInstance(document, res.getOutputStream());
			document.open();

			Paragraph paragraph = new Paragraph("ASSAN", font);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);

			Paragraph address = new Paragraph("Address:102, Shukrabad,Dhanmondi, Dhaka-1207", font1);
			address.setAlignment(Element.ALIGN_CENTER);
			document.add(address);

			Paragraph phone = new Paragraph("phone:098765432123", font1);
			phone.setAlignment(Element.ALIGN_CENTER);
			document.add(phone);

			Paragraph email = new Paragraph("Email:assan@gmail.com", font1);
			email.setAlignment(Element.ALIGN_CENTER);
			document.add(email);

			Paragraph monthlyReport = new Paragraph("Contact Information Report", font1);
			monthlyReport.setAlignment(Element.ALIGN_LEFT);
			document.add(monthlyReport);

			Paragraph pargraphdate = new Paragraph("Date: " + datetime, font1);
			pargraphdate.setAlignment(Element.ALIGN_LEFT);
			document.add(pargraphdate);

			float[] pointColumnWidths = { 15F, 18F, 15F, 15f };
			PdfPTable table = new PdfPTable(pointColumnWidths);
			table.setSpacingBefore(20);
			table.setWidthPercentage(100f);

			PdfPCell c1 = new PdfPCell(new Phrase("Name", font5));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);

			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Email", font5));

			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Subject", font5));
			c1.setPaddingBottom(7f);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Comment", font5));
			c1.setPaddingBottom(7f);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			table.setHeaderRows(1);
			c1.setPaddingBottom(10f);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);

			for (ContactInfo contact : contactinfoService.getList()) {

				PdfPCell cell_1 = new PdfPCell(new Phrase(contact.getName(), font6));
				cell_1.setPaddingBottom(6f);
				cell_1.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cell_2 = new PdfPCell(new Phrase((contact.getEmail()), font6));
				cell_2.setPaddingBottom(6f);
				cell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cell_3 = new PdfPCell(new Phrase((contact.getSubject()), font6));
				cell_3.setPaddingBottom(6f);
				cell_3.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cell_4 = new PdfPCell(new Phrase(contact.getComment()));
				cell_4.setPaddingBottom(6f);
				cell_4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell_1);
				table.addCell(cell_2);
				table.addCell(cell_3);
				table.addCell(cell_4);
			}
			document.add(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();

	}

}
