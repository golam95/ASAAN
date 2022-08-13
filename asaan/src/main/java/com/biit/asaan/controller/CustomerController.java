package com.biit.asaan.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.biit.asaan.dto.ProductDTO;
import com.biit.asaan.helper.CommonMsg;
import com.biit.asaan.model.CartItem;
import com.biit.asaan.model.ContactInfo;
import com.biit.asaan.model.FarmerSchedule;
import com.biit.asaan.model.Order;
import com.biit.asaan.model.ProdutCategory;
import com.biit.asaan.model.SellingMsg;
import com.biit.asaan.model.SellingProduct;
import com.biit.asaan.model.User;
import com.biit.asaan.service.BuyingProductService;
import com.biit.asaan.service.CartItemService;
import com.biit.asaan.service.ContactInfoService;
import com.biit.asaan.service.FarmerScheduleService;
import com.biit.asaan.service.OrderService;
import com.biit.asaan.service.SellingMsgService;
import com.biit.asaan.service.SellingProductService;
import com.biit.asaan.service.UserService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private UserService userService;

	@Autowired
	private ContactInfoService contactInfoService;

	@Autowired
	private SellingProductService sellproductService;

	@Autowired
	private SellingMsgService sellingMsgService;

	@Autowired
	private FarmerScheduleService farmerScheduleService;

	@Autowired
	private BuyingProductService buyproductService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/signup")
	public String signupFrom(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping("/addSignup")
	public String addUser(@ModelAttribute("user") User addUser, Model model) {

		User checkUser = userService.findByUserName(addUser.getUserName());

		if (checkUser != null) {
			model.addAttribute("user", new User());
			model.addAttribute("errorMsg", "User name already exists.");
			return "signup";
		}
		userService.save(addUser);
		model.addAttribute("successMsg", "Add successfully!!");
		return "signup";
	}

	@GetMapping("/contact")
	public String contactFrom(Model model) {
		model.addAttribute("contact", new ContactInfo());
		return "contact";
	}

	@PostMapping("/addContactInfo")
	public String addContact(@ModelAttribute("contact") ContactInfo addContact, Model model) {
		contactInfoService.saveContactInfo(addContact);
		model.addAttribute("addSuccess", "Successfully added..");
		return "contact";
	}

	@GetMapping("/reset")
	public String resetPasswordFrom() {
		return "resetPassword";
	}

	@PostMapping(value = "/saveDemo")
	@ResponseBody
	public String aaddemoAjax(@RequestBody ProdutCategory contactInfo) {
		System.out.println(contactInfo.getCategoryName());
		return contactInfo.getCategoryName();
	}

	@GetMapping("/customerPanel")
	public String customerPanelFrom(Model model, Principal principal) {
		if (principal != null) {
			model.addAttribute("trackProductList", farmerScheduleService.findByname(principal.getName()));
			User getUser = userService.findByUserName(principal.getName());
			model.addAttribute("userProfile", getUser);
			model.addAttribute("totoalMsg", sellingMsgService.countUserBaseMsg(getUser.getId()));
			model.addAttribute("totalSchedule", farmerScheduleService.countfarmerschedule(getUser.getId()));
		} else
			model.addAttribute("userProfile", new User());

		model.addAttribute("pageStatus", "product");
		model.addAttribute("sellProductList", sellproductService.getSellProductList());
		return "customerDashboard";
	}

	@GetMapping("/profile")
	public String customerProfile(Model model, Principal principal) {
		if (principal != null) {
			User getUser = userService.findByUserName(principal.getName());
			model.addAttribute("userProfile", getUser);
		} else
			model.addAttribute("userProfile", new User());

		return "customerProfile";
	}

	@PostMapping(value = "/sendMessage")
	@ResponseBody
	public String aaddemoAjax(@RequestBody SellingMsg addMsg, Principal principal) {
		String getStatus = sellingMsgService.saveSellingMsg(addMsg, principal.getName());
		return getStatus;
	}

	@PostMapping(value = "/saveSchedule")
	@ResponseBody
	public String farmerSchedulList(@RequestBody FarmerSchedule addSchedule, Principal principal) {
		String getStatus = farmerScheduleService.saveFarmerSchedule(addSchedule, principal.getName());
		return getStatus;
	}

	@GetMapping("/checkScheduledetails")
	@ResponseBody
	public boolean checkSchedule(@ModelAttribute("sellerTime") String sellerTime,
			@ModelAttribute("sellerDate") String sellerDate) {

		boolean status = false;

		FarmerSchedule checkSchedule = farmerScheduleService.fetctScheduleList(sellerDate, sellerTime);

		if (checkSchedule != null) {
			status = true;
		} else {
			status = false;
		}
		return status;
	}

	@PostMapping(value = "/updateSellerProfile")
	@ResponseBody
	public String updateSellerProfile(@RequestBody User user, Principal principal) {
		userService.updateUserProfile(user, principal.getName());
		return "1";
	}

	@GetMapping("/buyProuct")
	public String buyerprodcut(Model model, Principal principal) {
		if (principal != null) {
			model.addAttribute("Userstatus", "1");
			User getUser = userService.findByUserName(principal.getName());
			model.addAttribute("customerOrderList", orderService.findByuserId(getUser.getId()));

		} else
			model.addAttribute("Userstatus", "0");
		model.addAttribute("buyProductList", buyproductService.getbuyProductList());
		return "buyProduct";
	}

	@PostMapping(value = "/addCart")
	@ResponseBody
	public String addCart(@RequestBody CartItem addCart, Principal principal) {
		String getStatus = cartItemService.addToCart(addCart, principal.getName());
		return getStatus;
	}

	@GetMapping("/totalCartItem")
	@ResponseBody
	public int totalCartItem(Principal principal) {
		int count = 0;
		if (principal != null) {
			User getUser = userService.findByUserName(principal.getName());
			for (CartItem cart : cartItemService.findByuserId(getUser.getId())) {
				count += 1;
			}
		}
		return count;
	}

	@GetMapping("/viewCart")
	public String viewCart(Model model, Principal principal) {
		double totalPrice = 0.0;
		int totalQuantity = 0;
		String totalProduct = "";
		if (principal != null) {
			User getUser = userService.findByUserName(principal.getName());
			for (CartItem cart : cartItemService.findByuserId(getUser.getId())) {
				totalPrice += cart.getPrice();
				totalQuantity += cart.getQuantity();
				totalProduct += cart.getProductName() + ",";
			}
			model.addAttribute("cartListData", cartItemService.findByuserId(getUser.getId()));
			model.addAttribute("totalPrice", totalPrice);
			model.addAttribute("totalQuantity", totalQuantity);
			model.addAttribute("totalProduct", totalProduct);
		}
		return "cart";
	}

	@PostMapping(value = "/addOrder")
	@ResponseBody
	public String addOrder(@RequestBody Order addOrder, Principal principal) {
		String getStatus = orderService.addOrder(addOrder, principal.getName());
		return getStatus;
	}

	@PostMapping(value = "/resetPassword")
	@ResponseBody
	public String resetPassword(@RequestBody User user) {
		String getStatus = userService.resetPassword(user);
		return getStatus;
	}

	@GetMapping("/checkEmail")
	@ResponseBody
	public boolean checkEmail(@ModelAttribute("emailAddress") String emailAddress) {
		boolean status = false;
		User checkUser = userService.findByemail(emailAddress);
		if (checkUser != null) {
			status = true;
		} else {
			status = false;
		}
		return status;
	}

	@GetMapping("/checkCountry")
	@ResponseBody
	public String checkCountry(Principal principal, Model model) {
		String countryName = "";
		if (principal != null) {
			User checkUser = userService.findByUserName(principal.getName());
			countryName = checkUser.getCountryName();
		} else {
			countryName = "empty";
		}
		return countryName;
	}

	@GetMapping("/deleteCart")
	public String deleteCart(@RequestParam("delId") Long id) {
		cartItemService.deleteCart(id);
		return "redirect:/customer/viewCart";
	}

}
