SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_create_item_type]
(
    @NAME VARCHAR(50)
)
AS
BEGIN
    
    SET NOCOUNT ON

	INSERT INTO tipo_prenda
	VALUES
		(@NAME)

END
GO
