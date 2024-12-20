USE [prj_meal]
GO
/****** Object:  Table [dbo].[MealPlanDetails]    Script Date: 7/5/2024 5:32:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MealPlanDetails](
	[detailId] [int] IDENTITY(1,1) NOT NULL,
	[planId] [int] NULL,
	[dayOfWeek] [varchar](10) NULL,
	[menuId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[detailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderItem]    Script Date: 7/5/2024 5:32:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderItem](
	[orderItemId] [int] IDENTITY(1,1) NOT NULL,
	[orderId] [int] NULL,
	[menuId] [int] NULL,
	[quantity] [int] NULL,
	[price] [int] NULL,
 CONSTRAINT [PK_OrderItem] PRIMARY KEY CLUSTERED 
(
	[orderItemId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 7/5/2024 5:32:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[orderId] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NULL,
	[orderDate] [date] NULL,
	[totalAmount] [int] NULL,
	[phoneNumber] [nvarchar](15) NULL,
	[deliveryAddress] [nvarchar](200) NULL,
	[status] [nvarchar](50) NULL,
 CONSTRAINT [PK__Orders__0809335DA924E5A3] PRIMARY KEY CLUSTERED 
(
	[orderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PersonalMealPlan]    Script Date: 7/5/2024 5:32:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PersonalMealPlan](
	[planId] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NULL,
	[planName] [varchar](255) NULL,
	[startDate] [date] NULL,
	[weeks] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[planId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Recipes]    Script Date: 7/5/2024 5:32:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Recipes](
	[recipeId] [int] IDENTITY(1,1) NOT NULL,
	[menuId] [int] NULL,
	[recipeName] [nvarchar](100) NOT NULL,
	[ingredients] [nvarchar](500) NULL,
	[instructions] [nvarchar](2000) NULL,
PRIMARY KEY CLUSTERED 
(
	[recipeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 7/5/2024 5:32:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[userId] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[email] [nvarchar](100) NOT NULL,
	[phone] [nvarchar](15) NULL,
	[role] [nvarchar](20) NOT NULL,
	[status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[WeeklyMenus]    Script Date: 7/5/2024 5:32:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WeeklyMenus](
	[menuId] [int] IDENTITY(1,1) NOT NULL,
	[menuName] [nvarchar](100) NOT NULL,
	[description] [nvarchar](500) NULL,
	[dietType] [nvarchar](50) NULL,
	[date] [date] NULL,
	[price] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[menuId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[MealPlanDetails] ON 

INSERT [dbo].[MealPlanDetails] ([detailId], [planId], [dayOfWeek], [menuId]) VALUES (1, 1, N'Monday', 1)
INSERT [dbo].[MealPlanDetails] ([detailId], [planId], [dayOfWeek], [menuId]) VALUES (2, 1, N'Thursday', 4)
INSERT [dbo].[MealPlanDetails] ([detailId], [planId], [dayOfWeek], [menuId]) VALUES (3, 1, N'Tuesday', 4)
INSERT [dbo].[MealPlanDetails] ([detailId], [planId], [dayOfWeek], [menuId]) VALUES (4, 3, N'Monday', 1)
INSERT [dbo].[MealPlanDetails] ([detailId], [planId], [dayOfWeek], [menuId]) VALUES (5, 5, N'Monday', 4)
INSERT [dbo].[MealPlanDetails] ([detailId], [planId], [dayOfWeek], [menuId]) VALUES (6, 5, N'Tuesday', 5)
INSERT [dbo].[MealPlanDetails] ([detailId], [planId], [dayOfWeek], [menuId]) VALUES (7, 1, N'Friday', 1)
SET IDENTITY_INSERT [dbo].[MealPlanDetails] OFF
GO
SET IDENTITY_INSERT [dbo].[OrderItem] ON 

INSERT [dbo].[OrderItem] ([orderItemId], [orderId], [menuId], [quantity], [price]) VALUES (1, 2, 1, 2, 0)
INSERT [dbo].[OrderItem] ([orderItemId], [orderId], [menuId], [quantity], [price]) VALUES (2, 3, 2, 4, 120000)
INSERT [dbo].[OrderItem] ([orderItemId], [orderId], [menuId], [quantity], [price]) VALUES (3, 4, 2, 2, 60000)
INSERT [dbo].[OrderItem] ([orderItemId], [orderId], [menuId], [quantity], [price]) VALUES (4, 5, 3, 1, 25000)
SET IDENTITY_INSERT [dbo].[OrderItem] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([orderId], [userId], [orderDate], [totalAmount], [phoneNumber], [deliveryAddress], [status]) VALUES (1, 1, CAST(N'2024-07-04' AS Date), 0, N'0865341745', N'thạnh lợi, thạnh lộc, giồng riềng, kiên giang', N'Completed')
INSERT [dbo].[Orders] ([orderId], [userId], [orderDate], [totalAmount], [phoneNumber], [deliveryAddress], [status]) VALUES (2, 1, CAST(N'2024-07-04' AS Date), 0, N'0865341745', N'Thạnh lợi Thạnh Lộc giồng riềng kiên giang', N'Cancelled')
INSERT [dbo].[Orders] ([orderId], [userId], [orderDate], [totalAmount], [phoneNumber], [deliveryAddress], [status]) VALUES (3, 1, CAST(N'2024-07-05' AS Date), 120000, N'0865341745', N'cần thơ', N'Completed')
INSERT [dbo].[Orders] ([orderId], [userId], [orderDate], [totalAmount], [phoneNumber], [deliveryAddress], [status]) VALUES (4, 2, CAST(N'2024-07-05' AS Date), 60000, N'0865341745', N'Abc', N'Delivered')
INSERT [dbo].[Orders] ([orderId], [userId], [orderDate], [totalAmount], [phoneNumber], [deliveryAddress], [status]) VALUES (5, 3, CAST(N'2024-07-05' AS Date), 25000, N'0865341745', N'Tháº¡nh ÄÃ´ng tháº¡nh phÆ°á»c giá»ng riá»ng kiÃªn giang', N'Completed')
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[PersonalMealPlan] ON 

INSERT [dbo].[PersonalMealPlan] ([planId], [userId], [planName], [startDate], [weeks]) VALUES (1, 1, N'Demo', CAST(N'2024-07-05' AS Date), 4)
INSERT [dbo].[PersonalMealPlan] ([planId], [userId], [planName], [startDate], [weeks]) VALUES (2, 1, N'Demo', CAST(N'2024-07-04' AS Date), 4)
INSERT [dbo].[PersonalMealPlan] ([planId], [userId], [planName], [startDate], [weeks]) VALUES (3, 1, N' 3334444', CAST(N'2024-07-01' AS Date), 3)
INSERT [dbo].[PersonalMealPlan] ([planId], [userId], [planName], [startDate], [weeks]) VALUES (4, 1, N'2', CAST(N'2024-07-01' AS Date), 1)
INSERT [dbo].[PersonalMealPlan] ([planId], [userId], [planName], [startDate], [weeks]) VALUES (5, 1, N'22', CAST(N'2024-07-08' AS Date), 2)
INSERT [dbo].[PersonalMealPlan] ([planId], [userId], [planName], [startDate], [weeks]) VALUES (6, 2, N'Eat111', CAST(N'2024-07-15' AS Date), 1)
INSERT [dbo].[PersonalMealPlan] ([planId], [userId], [planName], [startDate], [weeks]) VALUES (7, 3, N'Eat', CAST(N'2024-07-08' AS Date), 1)
SET IDENTITY_INSERT [dbo].[PersonalMealPlan] OFF
GO
SET IDENTITY_INSERT [dbo].[Recipes] ON 

INSERT [dbo].[Recipes] ([recipeId], [menuId], [recipeName], [ingredients], [instructions]) VALUES (2, 2, N'Xin chào', N'1 ký ớt', N'2 ký tương cà. 3 ký nước')
SET IDENTITY_INSERT [dbo].[Recipes] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([userId], [username], [password], [email], [phone], [role], [status]) VALUES (1, N'user2003', N'123456789', N'KimLTCE170469@fpt.edu.vn', N'0865341742', N'admin', 1)
INSERT [dbo].[Users] ([userId], [username], [password], [email], [phone], [role], [status]) VALUES (2, N'user1', N'12', N'letankim2003@gmail.com', N'0355179544', N'user', 1)
INSERT [dbo].[Users] ([userId], [username], [password], [email], [phone], [role], [status]) VALUES (3, N'tankim2k3', N'12345678', N'letankim2810@gmail.com', N'0865341746', N'user', 1)
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
SET IDENTITY_INSERT [dbo].[WeeklyMenus] ON 

INSERT [dbo].[WeeklyMenus] ([menuId], [menuName], [description], [dietType], [date], [price]) VALUES (1, N'fff', N'fffff', N'vegan', CAST(N'2024-07-05' AS Date), 500000)
INSERT [dbo].[WeeklyMenus] ([menuId], [menuName], [description], [dietType], [date], [price]) VALUES (2, N'aaa', N'aaa', N'vegan', CAST(N'2024-07-06' AS Date), 30000)
INSERT [dbo].[WeeklyMenus] ([menuId], [menuName], [description], [dietType], [date], [price]) VALUES (3, N'fffa', N'aaaa', N'special', CAST(N'2024-07-07' AS Date), 25000)
INSERT [dbo].[WeeklyMenus] ([menuId], [menuName], [description], [dietType], [date], [price]) VALUES (4, N'fffa', N'aaaa', N'vegan', CAST(N'2024-07-08' AS Date), 17000)
INSERT [dbo].[WeeklyMenus] ([menuId], [menuName], [description], [dietType], [date], [price]) VALUES (5, N'abc', N'22222', N'vegan', CAST(N'2024-07-09' AS Date), 60000)
INSERT [dbo].[WeeklyMenus] ([menuId], [menuName], [description], [dietType], [date], [price]) VALUES (7, N'eee', N'ee', N'vegan', CAST(N'2024-07-03' AS Date), 53000)
INSERT [dbo].[WeeklyMenus] ([menuId], [menuName], [description], [dietType], [date], [price]) VALUES (8, N'CÆ¡m táº¥m', N'SiÃªu ngon', N'vegan', CAST(N'2024-07-07' AS Date), 12)
SET IDENTITY_INSERT [dbo].[WeeklyMenus] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Users__F3DBC572DCADB393]    Script Date: 7/5/2024 5:32:18 PM ******/
ALTER TABLE [dbo].[Users] ADD UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[MealPlanDetails]  WITH CHECK ADD FOREIGN KEY([menuId])
REFERENCES [dbo].[WeeklyMenus] ([menuId])
GO
ALTER TABLE [dbo].[MealPlanDetails]  WITH CHECK ADD FOREIGN KEY([planId])
REFERENCES [dbo].[PersonalMealPlan] ([planId])
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK__Orders__userId__2F10007B] FOREIGN KEY([userId])
REFERENCES [dbo].[Users] ([userId])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK__Orders__userId__2F10007B]
GO
ALTER TABLE [dbo].[PersonalMealPlan]  WITH CHECK ADD  CONSTRAINT [FK__PersonalM__userI__412EB0B6] FOREIGN KEY([userId])
REFERENCES [dbo].[Users] ([userId])
GO
ALTER TABLE [dbo].[PersonalMealPlan] CHECK CONSTRAINT [FK__PersonalM__userI__412EB0B6]
GO
ALTER TABLE [dbo].[Recipes]  WITH CHECK ADD FOREIGN KEY([menuId])
REFERENCES [dbo].[WeeklyMenus] ([menuId])
GO
